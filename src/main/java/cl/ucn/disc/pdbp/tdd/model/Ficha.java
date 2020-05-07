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

package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Ficha Veterinaria.
 *
 * @author Ignacio Santander Quiñones.
 */
public final class Ficha {

    /**
     * Numero de ficha
     */
    private final long numero;

    /**
     * Nombre del paciente
     */
    private final String nombrePaciente;

    /**
     * Especie: ej. canino, felino
     */
    private final String especie;

    /**
     * Fecha de nacimiento
     */
    private final ZonedDateTime fechaNacimiento;

    /**
     * Raza
     */
    private final String raza;

    /**
     * Sexo: macho/hembra
     */
    private final Sexo sexo;

    /**
     * Color: rojo cobrizo, cafe
     */
    private final String color;

    /**
     * Tipo: interno/externo
     */
    private final Tipo tipo;

    /**
     * The Constructor.
     *
     * @param numero, Numero de la Ficha.
     * @param nombrePaciente, Nombre del Paciente.
     * @param especie, Especie del Paciente.
     * @param fechaNacimiento, Fecha de Nacimiento del Paciente.
     * @param raza, Raza del Paciente.
     * @param sexo, Sexo del Paciente.
     * @param color, Color del Paciente.
     * @param tipo, Tipo del Paciente.
     */
    public Ficha(final long numero, final String nombrePaciente, final String especie, final ZonedDateTime fechaNacimiento, final String raza, final Sexo sexo, final String color, final Tipo tipo) {
        // TODO: Agregar validaciones.
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
    }

    /**
     * @return the Numero de ficha.
     */
    public long getNumero() {
        return this.numero;
    }

    /**
     * @return the Nombre del paciente.
     */
    public String getNombrePaciente() {
        return this.nombrePaciente;
    }

    /**
     * @return the Nombre de la especie.
     */
    public String getEspecie() {
        return this.especie;
    }

    /**
     * @return the Fecha de nacimiento.
     */
    public ZonedDateTime getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * @return the Raza del paciente.
     */
    public String getRaza() {
        return this.raza;
    }

    /**
     * @return the Sexo del paciente.
     */
    public Sexo getSexo() {
        return this.sexo;
    }

    /**
     * @return the Color del paciente.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @return the Tipo del paciente.
     */
    public Tipo getTipo() {
        return this.tipo;
    }




}
