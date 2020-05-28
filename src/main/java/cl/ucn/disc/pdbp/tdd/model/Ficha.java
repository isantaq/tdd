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

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clase Ficha Veterinaria.
 * @author Ignacio Santander Quiñones.
 */
@DatabaseTable(tableName = "Ficha")
public final class Ficha {

    /**
     * Id
     */
    @DatabaseField(generatedId = true)
    private Long id;
    /**
     * Numero de ficha
     */
    @DatabaseField(unique = true, canBeNull = false)
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
    @DatabaseField(persisterClass = ZonedDateTimeType.class, canBeNull = false)
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
     * El Duenio
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    //foreign = true significa uqe es un externo, canBeNull false hace referencia a que no puede haber ficha sin duenio
    //foreignAutoRefresh hace referencia a que en el repositorio dado un id da una clase; true es cada vez que vaya al repositorio
    // y tenga a la persona va a traer todos los archivos de la ficha y de la persona
    private Persona duenio;

    /**
     * La lista de controles
     */
    @ForeignCollectionField
    private ForeignCollection<Control> controlList;

    /**
     * The empty constructor.
     */
    Ficha(){
        //Constructor vacio
    }

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
     * @param duenio, Duenio del Paciente.
     */
    public Ficha(Long numero,
                 String nombrePaciente,
                 String especie,
                 ZonedDateTime fechaNacimiento,
                 String raza,
                 Sexo sexo,
                 String color,
                 Tipo tipo,
                 Persona duenio) {

        // Los atributos no pueden ser null
        if(nombrePaciente == null || raza == null || color == null){
            throw new NullPointerException("Ningun atributo puede ser null");
        }

        // Nombre, Raza, Color debe tener al menos 3 letras
        if (raza.length()<3 || color.length()<3 || nombrePaciente.length()<3) {
            throw new RuntimeException("El nombre, raza, color deben tener al menos 3 letras");
        }

        //if(fechaNacimiento.isAfter()){}
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
        this.duenio = duenio;
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

    /**
     * @return the Duenio del paciente.
     */
    public Persona getDuenio() {
        return this.duenio;
    }

    /**
     * @return the Id del control.
     */
    public Long getId() {
        return id;
    }

}
