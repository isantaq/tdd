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
     * Direccion
     */
    @DatabaseField(canBeNull = false)
    private String direccion;

    /**
     * Telefono Fijo
     */
    @DatabaseField()
    private Integer telefonoFijo;

    /**
     * Telfono Movil
     */
    @DatabaseField(canBeNull = false)
    private Integer telefonoMovil;

    /**
     * Email
     */
    @DatabaseField(canBeNull = false, index = true)
    private String email;

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
     *  Constructor of the Persona. TODO: El rut no debe existir anteirormente
     * ✓ El nombre no puede ser null.
     * ✓ El nombre debe tener al menos 3 letras.
     * ✓ El apellido no puede ser null.
     * ✓ El apellido debe tener al menos 3 letras.
     * ✓ El rut no puede ser null.
     * ✓ El rut debe ser valido.
     * - El rut no debe existir anteriormente en los registros.
     * ✓ La direccion no puede ser null
     * ✓ La direccion debe tener al menos 3 letras.
     * ✓ El telefono fijo no puede ser null.
     * ✓ El telefono fijo debe tener al menos 7 digitos.
     * ✓ El telefono movil no puede ser null.
     * ✓ El tenefono movil debe tener al menos 7 digitos.
     * ✓ El email no puede ser null.
     * -✓ El email debe ser valido.
     * @param nombre el nombre de la Persona.
     * @param apellido el apellido de la Persona.
     * @param rut el rut de la Persona.
     * @param direccion la direccion de la Persona.
     * @param telefonoFijo el telefonoFijo de la Persona.
     * @param telefonoMovil el telefonMovil de la Persona.
     * @param email el email de la Persona.
     */
    public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo, Integer telefonoMovil, String email) {

        // Nombre no debe ser Null
        if(nombre == null){
            throw new NullPointerException("El nombre no puede ser null");
        }

        // El Apellido no puede ser null
        if(apellido == null){
            throw new NullPointerException("El apellido no puede ser null");
        }

        // El Rut no puede ser null
        if(rut == null){
            throw new NullPointerException("El rut no puede ser null");
        }

        // La direccion no puede ser null
        if(direccion == null){
            throw new NullPointerException("La direccion no puede ser null");
        }

        // El telefono fijo no puede ser null
        if(telefonoFijo == null){
            throw new NullPointerException("El telefono fijo no puede ser null");
        }

        // El telefono movil no puede ser null
        if(telefonoMovil == null){
            throw new NullPointerException("El telefono movil no puede ser null");
        }

        // El email no puede ser null
        if(email == null){
            throw new NullPointerException("El email no puede ser null");
        }

        //Nombre debe tener al menos 3 letras.
        if(nombre.length()<3){
            throw new RuntimeException("El nombre debe tener al menos 3 letras");
        }

        //Apellido debe tener al menos 3 letras.
        if(apellido.length()<3){
            throw new RuntimeException("El apellido debe tener al menos 3 letras");
        }

        //Direccion debe tener al menos 3 letras.
        if(direccion.length()<3){
            throw new RuntimeException("La direccion debe tener al menos 3 letras");
        }

        // El telefono fijo debe tener al menos 7 letras.
        if(Integer.toString(telefonoFijo).length()<7){
            throw new RuntimeException("El telefono fijo debe tener al menos 7 letras");
        }

        // El telefono movil debe tener al menos 7 letras.
        if(Integer.toString(telefonoMovil).length()<7){
            throw new RuntimeException("El telefono movil debe tener al menos 7 letras");
        }

        //Rut debe ser valido
        if(!Validation.isRutValid(rut)){
            throw new RuntimeException("El rut debe ser valido");
        }

        //El email debe ser valido
        if(!Validation.isEmailValid(email)){
            throw new RuntimeException("El email debe ser valido");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.direccion = direccion;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.email = email;

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

    /**
     * @return the Id de la Persona
     */
    public Long getId() {
        return this.id;
    }

}
