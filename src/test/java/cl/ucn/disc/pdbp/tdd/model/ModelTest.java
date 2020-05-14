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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model test.
 */
public final class ModelTest {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(ModelTest.class);

    /**
     * Test the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut no puede ser null.
     * - El rut debe ser valido.
     */
    @Test
    public void testPersona() {

        log.debug("Testing Persona ..");

        // The data!
        log.debug(".. valid ..");
        String nombre = "Andrea";
        String apellido = "Contreras";
        String nombreApellido = nombre + " " + apellido;
        String rutOk = "152532873";
        String rutError = "15253287K";
        String direccion = "Calle Falsa 22222";
        Integer telefonoFijo = 2555562;
        Integer telefonoMovil = 815252522;
        String emailOk = "email.prueba@feik.com";

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk,direccion, telefonoFijo, telefonoMovil,emailOk);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                null,
                null,
                null,
                null,
                null,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre,
                null,
                null,
                null,
                null,
                null,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                apellido,
                null,
                null,
                null,
                null,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                null,
                rutOk,
                null,
                null,
                null,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                null,
                null,
                direccion,
                null,
                null,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                null,
                null,
                null,
                telefonoFijo,
                null,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                null,
                null,
                null,
                null,
                telefonoMovil,
                null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                null,
                null,
                null,
                null,
                null,
                emailOk));
        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre,
                apellido,
                rutError,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre,
                null,
                rutError,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(null,
                apellido,
                rutError,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre,
                null,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(null,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));

        // Testing the size of nombre
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("",
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("I",
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("I",
                "",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("I",
                "S",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ig",
                "",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ig",
                "S",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ig",
                "Sa",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ign",
                "",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ign",
                "S",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ign",
                "Sa",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ign",
                "San",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(RuntimeException.class, () -> new Persona("Ignacio",
                "Santander",
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));

        log.debug("Done.");

    }

    /**
     * Test the digito verificador.
     */
    @Test
    @Disabled // remove to run
    public void testDigitoVerificador() {

        Assertions.assertFalse(Validation.isRutValid(null));

        Assertions.assertTrue(Validation.isRutValid("152532873"));
        Assertions.assertTrue(Validation.isRutValid("21195194K"));
        Assertions.assertTrue(Validation.isRutValid("121244071"));
        Assertions.assertTrue(Validation.isRutValid("198127949"));
        Assertions.assertTrue(Validation.isRutValid("202294316"));

        Assertions.assertFalse(Validation.isRutValid("1525A2873"));
        Assertions.assertFalse(Validation.isRutValid("15253287K"));
        Assertions.assertFalse(Validation.isRutValid("15253287-"));


    }

}
