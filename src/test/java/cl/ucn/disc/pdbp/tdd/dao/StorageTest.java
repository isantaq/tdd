/*
 * MIT License
 *
 * Copyright (c) 2020 Ignacio Santander Quiñones <ignacio.santander@alumnos.ucn.cl>.
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

package cl.ucn.disc.pdbp.tdd.dao;

import checkers.nullness.quals.AssertNonNullIfNonNull;
import cl.ucn.disc.pdbp.tdd.model.*;
import cl.ucn.disc.pdbp.utils.Entity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Storage Test
 * @author Ignacio Santander Quiñones.
 */
public final class StorageTest {


    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing the Repository of Ficha
     */
    @Test
    public void testRepositoryFicha(){
        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){
            //TODO: Include this call in the repository
            //Create the table Persona
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            //Create the table Ficha
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            //Create the table Control
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            Repository<Ficha, Long> repositoryFicha = new RepositoryOrmLite<>(connectionSource,Ficha.class);
            Repository<Persona, Long> repositoryPersona = new RepositoryOrmLite<>(connectionSource,Persona.class);


            //1. Crear la Persona desde un Repository
            Persona duenio = new Persona("Andrea",
                    "Contreras",
                    "152532873",
                    "Calle Falsa 123",
                    5895555,
                    548855558,
                    "andrea.contreras@feik.com");
            //Crear el objeto en la base de datos
            if(!repositoryPersona.create(duenio)){
                Assertions.fail("Can't insert Persona!");
            }

            // 2. Instanciar una Ficha pasando la persona como parametro del constructor
            Ficha ficha = new Ficha(123L,
                    "Firulais",
                    "Canino",
                    ZonedDateTime.now(),
                    "Pastor Ingles",
                    Sexo.MACHO,
                    "Negro",
                    Tipo.INTERNO,
                    duenio);
            // Crear el objeto en la base de datos
            if(!repositoryFicha.create(ficha)){
                Assertions.fail("Can't insert the Ficha!");
            }

            // 3. Obtener una ficha y revisar si sus atributos son distintos de null.
            // 1L = Long.valueof(1) = definir el tipo
            Ficha ficha2 = repositoryFicha.findById(1L);
            // La ficha no puede ser nul!!
            Assertions.assertNotNull(ficha2, "Ficha was null");
            Assertions.assertNotNull(ficha2.getDuenio(),"Duenio de Ficha was null");
            Assertions.assertNotNull(ficha2.getDuenio().getRut(),"Rut del Duenio de Ficha was null");
            Assertions.assertNotNull(ficha2.getFechaNacimiento(),"FechaNacimiento was null");
            Assertions.assertEquals(ficha.getNombrePaciente(),ficha2.getNombrePaciente(),"{Ficha} no son iguales");

            Entity entity = new Entity();

            //No es necesario porque se updeta solo
            //repositoryFicha.update(ficha2);

            log.debug("Ficha: {}",entity.toString(ficha2));
            //Imprimir los atributos de la ficha
        }catch(SQLException | IOException exception){
            throw new RuntimeException(exception);
        }


    }

    /**
     * Testing the Repository of Control
     */
    @Test
    public void testRepositoryControl() {
        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {
            //TODO: Include this call in the repository
            //Create the table Persona
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            //Create the table Ficha
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            //Create the table Control
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            Repository<Ficha, Long> repositoryFicha = new RepositoryOrmLite<>(connectionSource, Ficha.class);
            Repository<Persona, Long> repositoryPersona = new RepositoryOrmLite<>(connectionSource, Persona.class);
            Repository<Control, Long> repositoryControl = new RepositoryOrmLite<>(connectionSource, Control.class);

            //1. Crear la Persona desde un Repository
            Persona duenio = new Persona("Andrea",
                    "Contreras",
                    "152532873",
                    "Calle Falsa 123",
                    5895555,
                    548855558,
                    "andrea.contreras@feik.com");
            //Crear el objeto en la base de datos
            if (!repositoryPersona.create(duenio)) {
                Assertions.fail("Can't insert Persona!");
            }

            // 2. Instanciar una Ficha pasando la persona como parametro del constructor
            Ficha ficha = new Ficha(123L,
                    "Firulais",
                    "Canino",
                    ZonedDateTime.now(),
                    "Pastor Ingles",
                    Sexo.MACHO,
                    "Negro",
                    Tipo.INTERNO,
                    duenio);

            // Crear el objeto en la base de datos
            if (!repositoryFicha.create(ficha)) {
                Assertions.fail("Can't insert the Ficha!");
            }
            // 4. Crear la Persona que es el veterinario al cual se le pasara por parametro del constructor
            Persona veterinario = new Persona("Ignacio",
                    "Santander",
                    "193991769",
                    "Calle Verdadera 133",
                    552771070,
                    968332149,
                    "ignacio.santander@alumnos.ucn.cl");
            // Crear el objeto en la base de datos
            if(!repositoryPersona.create(veterinario)){
                Assertions.fail("Can't insert Persona!");
            }

            // 4. Crear la Persona que es el veterinario al cual se le pasara por parametro del constructor
            Control control = new Control(ZonedDateTime.now(),
                    ZonedDateTime.now().plusDays(1),
                    35.5d,
                    2d,
                    1.5d,
                    "Todo Ok",
                    veterinario,
                    ficha);
            // Crear el objeto en la base de datos
            if(!repositoryControl.create(control)){
                Assertions.fail("Can't insert Persona!");
            }

            // 4. Obtener una ficha y revisar si sus atributos son distintos de null.
            // 1L = Long.valueof(1) = definir el tipo
            Ficha ficha2 = repositoryFicha.findById(1L);
            // La ficha no puede ser nul!!
            Assertions.assertNotNull(ficha2, "Ficha was null");
            Assertions.assertNotNull(ficha2.getDuenio(), "Duenio de Ficha was null");
            Assertions.assertNotNull(ficha2.getDuenio().getRut(), "Rut del Duenio de Ficha was null");
            Assertions.assertNotNull(ficha2.getFechaNacimiento(), "FechaNacimiento was null");
            Assertions.assertEquals(ficha.getNombrePaciente(), ficha2.getNombrePaciente(), "{Ficha} no son iguales");

            Entity entity = new Entity();

            //No es necesario porque se updeta solo
            //repositoryFicha.update(ficha2);
            log.debug("Ficha: {}", entity.toString(ficha2));
            log.debug("Control: {}", entity.toString(control));

            //Imprimir los atributos de la ficha
        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Testing de ORMLite + H2 (database)
     */
    @Test
    public void testDatabase() throws SQLException {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Connection source: autoclose with the try/catch
        try (ConnectionSource conectionSource = new JdbcConnectionSource(databaseUrl)){

            // Create the table from Persona annotations
            TableUtils.createTableIfNotExists(conectionSource, Persona.class);

            // The dao of Persona
            Dao<Persona,Long> daoPersona = DaoManager.createDao(conectionSource,Persona.class);

            // New Persona
            Persona persona = new Persona("Andrea","Contreras","152532873","Calle Falsa 123", 5895555,54888,"andrea.contreras@feik.com");

            // Insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("Id: {}",persona.getId());
            //
            Assertions.assertEquals(1,tuples,"Save tuples != 1");


            // Get from db
            Persona personaDb = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(),personaDb.getNombre(), "Nombre not equals!");
            Assertions.assertEquals(persona.getApellido(),personaDb.getApellido(),"Apellido not equals!");
            Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"Rut not equals!");

            // Search by rut: SELECT * FROM 'persona' WHERE 'rut' = '152532873'
            List<Persona> personaList = daoPersona.queryForEq("rut","152532873");
            Assertions.assertEquals(1,personaList.size(),"More than one person?!");

            // Not found by rut
            Assertions.assertEquals(0,daoPersona.queryForEq("rut", "19").size(), "Found something !?");

        }catch (IOException e){
            log.error("Error", e);
        }
    }

}
