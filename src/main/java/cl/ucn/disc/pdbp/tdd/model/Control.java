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

package cl.ucn.disc.pdbp.tdd.model;

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.field.DatabaseField;

import java.time.ZonedDateTime;

/**
 * Clase Control
 * @author Ignacio Santander Quiñones.
 */
public final class Control {

    /**
     * ID
     */
    @DatabaseField(generatedId = true)
    private Long idControl;

    /**
     * Fecha
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class, canBeNull = false)
    private ZonedDateTime fecha;

    /**
     * Fecha del Proximo Control
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class, canBeNull = false)
    private ZonedDateTime fechaProximoControl;

    /**
     * Temperatura
     */
    @DatabaseField(canBeNull = false)
    private Double temperatura;

    /**
     * Peso
     */
    @DatabaseField(canBeNull = false)
    private Double peso;

    /**
     * Altura
      */
    @DatabaseField(canBeNull = false)
    private Double altura;

    /**
     * Diagnostico
     */
    @DatabaseField(canBeNull = false)
    private String diagnostico;

    /**
     * Veterinario encargado
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Persona veterinario;

    /**
     * La ficha
     */
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Ficha ficha;

    /**
     * The empty Constuctor
     */
    Control(){
        //Constructor vacio.
    }

    /**
     * Constructor of la clase Control TODO: Agregar Validaciones
     * - Fecha tiene que ser el dia de hoy.
     * ✓ Temperatura no puede ser null.
     * ✓ Temperatura no puede ser negativa.
     * - Temperatura no puede tener caracteres, excluyendo ","
     * ✓ Peso no puede ser null.
     * ✓ Peso no puede ser negativo.
     * - Peso no puede tener caracteres, excluyendo ","
     * ✓ Altura no puede ser null.
     * ✓ Altura no puede ser negativa.
     * - Altura no puede tener caracteres, excluyento ","
     * ✓ Diagnostico no puede ser null.
     * ✓ Nombre de veterinario no puede ser null.
     * - Nombre de veterinario debe existir.
     * - Fecha proximo Control no puede ser una pasada.
     * - Fecha proximo control no puede ser hoy.
     * @param fecha fecha del Control.
     * @param fechaProximoControl fecha del proximo Control.
     * @param temperatura temperatura del paciente.
     * @param peso peso del Paciente.
     * @param altura altura del Paciente.
     * @param diagnostico diagnostico del Paciente.
     * @param veterinario veterinario encargado del Control.
     * @param ficha la ficha donde se guardara el Control.
     */
    public Control(ZonedDateTime fecha,
                   ZonedDateTime fechaProximoControl,
                   Double temperatura,
                   Double peso,
                   Double altura,
                   String diagnostico,
                   Persona veterinario,
                   Ficha ficha){

        // La temperatura no puede ser null
        if(temperatura == null) throw new NullPointerException("La temperatura no puede ser null");

        // El peso no puede ser null
        if(peso == null) throw new NullPointerException("El peso no puede ser null");

        // La altura no puede ser null
        if(altura == null) throw new NullPointerException("La altura no puede ser null");

        // El diagnostico no puede ser null
        if(diagnostico == null) throw new NullPointerException("El diagnostico no puede ser null");

        // El veterinario no puede ser null
        if(veterinario == null) throw new NullPointerException("El veterinario no puede ser null");


        // La temperatura no puede ser negativa
        if(temperatura < 0){
            throw new RuntimeException("La temperatura no puede ser negativa");

        }

        // El peso no puede ser negativo
        if (peso < 0) {
            throw new RuntimeException("El peso no puede ser negativa");
        }

        // La altura no puede ser negativa
        if(altura < 0){
            throw new RuntimeException("La altura no puede ser negativa");
        }

        this.fecha = fecha;
        this.fechaProximoControl = fechaProximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
        this.ficha = ficha;
    }

    /**
     * @return the fecha del control
     */
    public ZonedDateTime getFecha() {
        return fecha;
    }

    /**
     * @return the fecha del proximo control
     */
    public ZonedDateTime getFechaProximoControl(){
        return fechaProximoControl;
    }

    /**
     * @return the temperatura del paciente  del control
     */
    public Double getTemperatura(){
        return temperatura;
    }

    /**
     * @return the peso del paciente del control
     */
    public Double getPeso(){
        return peso;
    }

    /**
     * @return the altura del paciente del control
     */
    public Double getAltura(){
        return altura;
    }

    /**
     * @return the diagnostico del control
     */
    public String getDiagnostico(){
        return  diagnostico;
    }

    /**
     * @return the veterinario encargado del control
     */
    public Persona getVeterinario(){
        return veterinario;
    }

    /**
     * @return the ficha donde esta el control
     */
    public Ficha getFicha(){
        return ficha;
    }

    /**
     * @return the id del control
     */
    public Long getIdControl() {
        return idControl;
    }
}
