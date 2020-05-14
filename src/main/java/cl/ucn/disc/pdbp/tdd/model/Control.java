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
 * Clase Control
 * @author Ignacio Santander Quiñones.
 */
public final class Control {

    /**
     * ID
     */
    private Long idControl;

    /**
     * Fecha
     */
    private ZonedDateTime fecha;

    /**
     * Fecha del Proximo Control
     */
    private ZonedDateTime fechaProximoControl;

    /**
     * Temperatura
     */
    private Double temperatura;

    /**
     * Peso
     */
    private Double peso;

    /**
     * Altura
      */
    private Double altura;

    /**
     * Diagnostico
     */
    private String diagnostico;

    /**
     * Veterinario encargado
     */
    private String veterinario;

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
     */
    public Control(ZonedDateTime fecha, ZonedDateTime fechaProximoControl, Double temperatura, Double peso, Double altura, String diagnostico, String veterinario){

        // La temperatura no puede ser null
        if(temperatura == null){
            throw new NullPointerException("La temperatura no puede ser null");
        }

        // El peso no puede ser null
        if(peso == null){
            throw new NullPointerException("El peso no puede ser null");
        }

        // La altura no puede ser null
        if(altura == null){
            throw new NullPointerException("La altura no puede ser null");
        }

        // El diagnostico no puede ser null
        if(diagnostico == null){
            throw new NullPointerException("El diagnostico no puede ser null");
        }

        // El veterinario no puede ser null
        if(veterinario == null){
            throw new NullPointerException("El veterinario no puede ser null");
        }

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
    }
}
