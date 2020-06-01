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

import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import java.util.List;

/**
 * @author Ignacio Santander Quiñones
 */
public interface Contratos {

    /**
     * Contrato: C01 - Registrar los datos de un Paciente.
     * @param ficha la ficha
     * @return la ficha
     */
    Ficha registrarPaciente(Ficha ficha);

    /**
     * Contrato: C02 - Registrar los datos de una Persona.
     * @param persona la persona
     * @return la persona
     */
    Persona registrarPersona(Persona persona);

    /**
     *  Contrato: C03 - Buscar una Ficha.
     * @param query la consulta
     * @return la lista de fichas
     */
    List<Ficha> buscarFicha(String query);

    /**
     * Ruta 1 GET
     * @return la lista de todas las fichas.
     */
    List<Ficha> getAllFichas();

    /**
     * Ruta 1 POST getPersona para duenio
     */
    Persona getPersona(Long idDuenio);

    /**
     * Ruta 3 GET
     * @return la lista de todas las personas.
     */
    List<Persona> getAllPersonas();

    /**
     * Ruta 5 GET
     * @param numero de la ficha
     * @return la lista de todos los Controles de una Ficha.
     */
    List<Control> getControlesOfFicha(Long numero);

    /**
     * Ruta 5 POST
     * @param numero de la ficha
     * @return la ficha.
     */
    Ficha getFicha(Long numero);

    /**
     * Ruta 5 POST
     * @param control el control a registrar
     * @return el control.
     */
    Control registrarControl(Control control);

    /**
     * Ruta 6 GET
     * @param numero de la ficha
     * @return la Persona (Duenio) de Ficha.
     */
    Persona getDuenioOfFicha(Long numero);



}
