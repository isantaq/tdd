/*
 * MIT License
 *
 * Copyright (c) 2020 Ignacio Santander Quiñones <ignacio.santander@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.dao.Repository;
import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;

import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Implementation of Contratos.
 * @author Ignacio Santander Quiñones
 */
public class ContratosImpl implements Contratos{

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

    /**
     * ConnectionSource
     */
    private ConnectionSource connectionSource;

    /**
     * Repositorio Ficha
     */
    private Repository<Ficha, Long> repoFicha;

    /**
     * Repositorio Persona
     */
    private Repository<Persona, Long> repoPersona;

    /**
     * Repositorio Control
     */
    private Repository<Control, Long> repoControl;

    /**
     * The Constructor.
     * @param databaseUrl jdbc string to connect to backend.
     */
    public ContratosImpl(String databaseUrl){
        if(databaseUrl == null) throw new IllegalArgumentException("Can't create Contratos with databaseUrl null");

        try{
            this.connectionSource = new JdbcConnectionSource(databaseUrl);

            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            this.repoFicha = new RepositoryOrmLite<>(this.connectionSource,Ficha.class);
            this.repoPersona = new RepositoryOrmLite<>(this.connectionSource,Persona.class);
            this.repoControl = new RepositoryOrmLite<>(this.connectionSource,Control.class);

        }catch(SQLException throwables ){
            throw new RuntimeException(throwables);
        }

    }

    /**
     * Contrato: C01 - Registrar los datos de un Paciente.
     * @param ficha la ficha del paciente
     * @return la ficha
     */
    @Override
    public Ficha registrarPaciente(Ficha ficha) {

        // Nullity
        if (ficha == null) throw new IllegalArgumentException("Ficha was null!");

        // Crear persona en la BD
        try{
            this.repoFicha.create(ficha);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        // Obtengo Ficha de la BD
        Ficha ficha2 = repoFicha.findById(ficha.getId());
        return ficha2;
    }

    /**
     * Contrato: C02 - Registrar los datos de una Persona.
     * @param persona la persona a registrar
     * @return la persona
     */
    @Override
    public Persona registrarPersona(Persona persona) {

        // Nullity
        if (persona == null) throw new IllegalArgumentException("Persona was null!");
        // Crear persona en la BD
        try {
            this.repoPersona.create(persona);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        // Obtengo persona de la BD
        Persona persona2 = repoPersona.findById(persona.getId());
        return persona2;
    }

    /**
     * Contrato: C03 - Buscar una Ficha.
     * @param query la consulta
     * @return la lista de fichas
     */
    @Override
    public List<Ficha> buscarFicha(String query) {

        // Nullity
        if (query == null) throw new IllegalArgumentException("Query was null!");

        // The result: List of Ficha.
        List<Ficha> fichas = new ArrayList<>();

        try{
            // 1. Find by number
            if (StringUtils.isNumeric(query)){
                // All the Fichas with number
                log.debug("Finding Fichas with numero ..");
                fichas.addAll(this.repoFicha.findAll("numero", query));

                // 2. Find by rut of Duenio
                log.debug("Finding Fichas with rut of duenio ..");
                QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
                queryPersona.where().like("rut", "%" + query + "%");

                // Run the Join
                fichas.addAll(this.repoFicha
                        .getQuery()
                        .join(queryPersona)
                        .query());
            }

            // 3. Find by name of Paciente
            log.debug("Finding Fichas with nombre of Paciente");
            fichas.addAll(this.repoFicha.getQuery()
                    .where()
                    .like("nombrePaciente", "%" + query + "%")
                    .query());

            // 4. Find by rut of Duenio
            log.debug("Finding Fichas with nombre of duenio ..");
            QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
            queryPersona.where().like("nombre", "%" + query + "%");

            // Run the Join
            fichas.addAll(this.repoFicha
                    .getQuery()
                    .join(queryPersona)
                    .query());


        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }

        // Remove duplicated (by id)
        return new ArrayList<>(
                fichas.stream().collect(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Ficha::getId)))
                )
        );
    }

    /**
     * Ruta 1 GET
     * @return la lista de todas las Fichas.
     */
    @Override
    public List<Ficha> getAllFichas(){
        return this.repoFicha.findAll();
    }

    /**
     * Ruta 1 POST getPersona para duenio
     */
    @Override
    public Persona getPersona(Long idDuenio) {
        return this.repoPersona.findAll("id",idDuenio).get(0);
    }


    /**
     * Ruta 3 GET
     * @return la lista de todas las Personas.
     */
    @Override
    public List<Persona> getAllPersonas(){
        return this.repoPersona.findAll();
    }

    /**
     * Ruta 5 GET
     * @return la lista de todas las Personas.
     */
    @Override
    public List<Control> getControlesOfFicha(Long numero){
        return this.repoFicha.findAll("numero", numero).get(0).getControles();
    }

    /**
     * Ruta 5 POST
     * @return la lista.
     */
    @Override
    public Ficha getFicha(Long numero){
        return this.repoFicha.findAll("numero",numero).get(0);
    }

    /**
     * Ruta 5 POST
     * @param control control a registrar
     * @return el control
     */
    public Control registrarControl(Control control){

        // Nullity
        if (control == null) throw new IllegalArgumentException("Control was null!");
        // Crear persona en la BD
        try {
            this.repoControl.create(control);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        // Obtengo persona de la BD
        Control control2 = repoControl.findById(control.getIdControl());
        return control2;
    }

    /**
     * Ruta 6
     * @param numero de la ficha
     * @return la Persona (Duenio) de Ficha.
     */
    @Override
    public Persona getDuenioOfFicha(Long numero) {
        return this.repoFicha.findAll("numero", numero).get(0).getDuenio();
    }
    // Puede retornar un null findDuenioOfFicha, numero no puede ser null, no puede ser negativo

}
