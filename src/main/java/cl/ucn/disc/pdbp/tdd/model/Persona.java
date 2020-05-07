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

import cl.ucn.disc.pdbp.utils.Validation;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Clase Persona
 * @author Ignacio Santander Quiñones.
 */
@DatabaseTable(tableName = "Persona")
public final class Persona {

    /**
     * ID (KEY)
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * Nombre
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * Apellido
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * Rut
     */
    @DatabaseField(canBeNull = false, index = true)
    private String rut;

    /**
     * Constructor Vacio
     */
    Persona(){
        //Constructor vacio para DatabaseField
    }

    /**
     * Constructor the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut no puede ser null.
     * - El rut debe ser valido.
     * @param nombre, Nombre de la Persona.
     * @param apellido, Apellido de la Persona.
     * @param rut, Rut de la Persona.
     */
    public Persona(String nombre, String apellido, String rut) {

        // Nombre, Apellido y Rut no deben ser Null
        if(nombre == null | apellido == null | rut == null){
            throw new NullPointerException("El nombre, apelllido o rut no pueden ser null");
        }

        //Nombre debe tener al menos 2 letras.
        if(nombre.length()<2){
            throw new RuntimeException("El nombre debe tener al menos 2 letras");
        }

        //Apellido debe tener al menos 3 letras.
        if(apellido.length()<3){
            throw new RuntimeException("El apellido debe tener al menos 3 letras");
        }

        //Rut debe ser valido
        if(!Validation.isRutValid(rut)){
            throw new RuntimeException("El rut debe ser valido");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;

    }

    /**
     * @return the Nombre de la Persona.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return the Apellido de la Persona.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * @return the Rut de la Persona.
     */
    public String getRut(){
        return this.rut;
    }

    /**
     * @return the Nombre y Apellido de la Persona.
     */
    public String getNombreApellido() {
        return (nombre + " " + apellido);
    }

}
