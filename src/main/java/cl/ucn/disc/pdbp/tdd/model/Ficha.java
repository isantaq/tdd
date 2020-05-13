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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.ZonedDateTime;

/**
 * Clase Ficha Veterinaria.
 * @author Ignacio Santander Quiñones.
 */
@DatabaseTable(tableName = "Ficha")
public final class Ficha {

    /**
     * Numero de ficha
     */
    @DatabaseField(canBeNull = false)
    private Long numero;

    /**
     * Nombre del paciente
     */
    @DatabaseField(canBeNull = false)
    private String nombrePaciente;

    /**
     * Especie: ej. canino, felino
     */
    @DatabaseField(canBeNull = false)
    private String especie;

    /**
     * Fecha de nacimiento
     */
    @DatabaseField(canBeNull = false)
    private ZonedDateTime fechaNacimiento;

    /**
     * Raza
     */
    @DatabaseField(canBeNull = false)
    private String raza;

    /**
     * Sexo: macho/hembra
     */
    @DatabaseField(canBeNull = false)
    private Sexo sexo;

    /**
     * Color: rojo cobrizo, cafe
     */
    @DatabaseField(canBeNull = false)
    private String color;

    /**
     * Tipo: interno/externo
     */
    @DatabaseField(canBeNull = false)
    private Tipo tipo;


    /**
     * The Constructor of the Ficha. TODO: Validar Fecha
     * ✓ El nombre no puede ser null.
     * ✓ El nombre debe tener al menos 3 letras.
     * - Fecha no puede ser futura.
     * - Fecha no puede ser muy al pasado.
     * ✓ La raza no puede ser null.
     * ✓ La raza debe tener al menos 3 letras.
     * ✓ El color no puede ser vacio.
     * ✓ El color debe tener al menos 3 letras.
     * @param nombrePaciente, Nombre del Paciente.
     * @param especie, Especie del Paciente.
     * @param fechaNacimiento, Fecha de Nacimiento del Paciente.
     * @param raza, Raza del Paciente.
     * @param sexo, Sexo del Paciente.
     * @param color, Color del Paciente.
     * @param tipo, Tipo del Paciente.
     */
    public Ficha(String nombrePaciente, String especie, ZonedDateTime fechaNacimiento, String raza, Sexo sexo, String color, Tipo tipo) {

        // Nombre no puede ser null
        if(nombrePaciente == null){
            throw new NullPointerException("El nombre no puede ser null");
        }

        // Raza no puede ser null
        if(raza == null){
            throw new NullPointerException("La raza no puede ser null");
        }

        // Color no puede ser null
        if(color == null){
            throw new NullPointerException("El color no puede ser null");
        }

        // Nombre debe tener al menos 3 letras
        if(nombrePaciente.length()<3){
            throw new RuntimeException("El nombre debe tener al menos 3 letras");
        }

        // Raza debe tener al menos 3 letras
        if (raza.length()<3) {
            throw new RuntimeException("La raza debe tener al menos 3 letras");
        }

        // Color debe tener al menos 3 letras
        if(color.length()<3){
            throw new RuntimeException("El color debe tener al menos 3 letras");
        }
        //if(fechaNacimiento.isAfter()){}
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
