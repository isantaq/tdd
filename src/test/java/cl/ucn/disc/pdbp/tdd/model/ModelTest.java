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

import cl.ucn.disc.pdbp.utils.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

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
        String nombreError = "An";
        String apellido = "Contreras";
        String apellidoError = "Co";
        String nombreApellido = nombre + " " + apellido;
        String rutOk = "152532873";
        String rutError = "15253287K";
        String direccion = "Calle Falsa 22222";
        String direccionError = "C";
        Integer telefonoFijo = 2555562;
        Integer telefonoFijoError = 2;
        Integer telefonoMovil = 815252522;
        Integer telefonoMovilError = 8;
        String emailOk = "email.prueba@feik.com";
        String emailError = "hola.cl";

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk, direccion, telefonoFijo, telefonoMovil, emailOk);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);
        Assertions.assertEquals(persona.getRut(), rutOk);
        Assertions.assertEquals(persona.getTelefonoFijo(), telefonoFijo);
        Assertions.assertEquals(persona.getTelefonoMovil(), telefonoMovil);
        Assertions.assertEquals(persona.getEmail(), emailOk);

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre,
                null,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre,
                apellido,
                null,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                apellido,
                rutOk,
                null,
                telefonoFijo,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                apellido,
                rutOk,
                direccion,
                null,
                telefonoMovil,
                emailOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                null,
                emailOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                null));


        // Testing invalid name
        log.debug("... name...");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombreError,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailOk));

        // Testing invalid last name
        log.debug("... last name...");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre,
                apellidoError,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
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

        // Testing invalid direccion
        log.debug("... adress...");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombreError,
                apellido,
                rutOk,
                direccionError,
                telefonoFijo,
                telefonoMovil,
                emailOk));

        // Testing invalid telefonoFijo
        log.debug("... phone...");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombreError,
                apellido,
                rutOk,
                direccion,
                telefonoFijoError,
                telefonoMovil,
                emailOk));

        // Testing invalid telefonoMovil
        log.debug("... movil phone...");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombreError,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovilError,
                emailOk));

        // Testing invalid email
        log.debug("... email...");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombreError,
                apellido,
                rutOk,
                direccion,
                telefonoFijo,
                telefonoMovil,
                emailError));


        log.debug("Done.");

    }

    /**
     * Test the digito verificador.
     */
    @Test
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

    /**
     * Test the Control
     */
    @Test
    public void testControl() {

        log.debug("Testing Control ..");
        // The data!
        log.debug(".. valid ..");
        ZonedDateTime fecha = ZonedDateTime.now();
        ZonedDateTime fechaError = ZonedDateTime.now().minusDays(1);
        ZonedDateTime fechaProximoControl = ZonedDateTime.now().plusDays(1);
        ZonedDateTime fechaProximoControlError = ZonedDateTime.now();
        Double temperatura = 35.4d;
        Double temperaturaError = -9d;
        Double peso = 2d;
        Double pesoError = -9d;
        Double altura = 10d;
        Double alturaError = -9d;
        String diagnostico = "Todo Ok";
        Persona duenio = new Persona("Andrea",
                "Contreras",
                "152532873",
                "Calle Falsa 123",
                2555562,
                815252522,
                "email.prueba@feik.com");

        Persona veterinario = new Persona("Ignacio",
                "Santander",
                "193991769",
                "Calle Verdadera 133",
                552771070,
                968332149,
                "ignacio.santander@alumnos.ucn.cl");

        Ficha ficha = new Ficha(123L,
                "Firulais",
                "Canino",
                ZonedDateTime.now(),
                "Pastor Ingles",
                Sexo.MACHO,
                "Negro",
                Tipo.INTERNO,
                duenio);

        // Test constructor and getters
        Control control = new Control(fecha, fechaProximoControl, temperatura, peso, altura, diagnostico, veterinario, ficha);
        Assertions.assertEquals(control.getFecha(), fecha);
        Assertions.assertEquals(control.getFechaProximoControl(), fechaProximoControl);
        Assertions.assertEquals(control.getTemperatura(), temperatura);
        Assertions.assertEquals(control.getPeso(), peso);
        Assertions.assertEquals(control.getAltura(), altura);
        Assertions.assertEquals(control.getDiagnostico(), diagnostico);
        Assertions.assertEquals(control.getVeterinario(), veterinario);

        // Testing nullity.
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Control(null, fechaProximoControl, temperatura, peso, altura, diagnostico, veterinario, ficha));
        Assertions.assertThrows(NullPointerException.class, () -> new Control(fecha, fechaProximoControl, null, peso, altura, diagnostico, veterinario, ficha));
        Assertions.assertThrows(NullPointerException.class, () -> new Control(fecha, fechaProximoControl, temperatura, null, altura, diagnostico, veterinario, ficha));
        Assertions.assertThrows(NullPointerException.class, () -> new Control(fecha, fechaProximoControl, temperatura, peso, null, diagnostico, veterinario, ficha));
        Assertions.assertThrows(NullPointerException.class, () -> new Control(fecha, fechaProximoControl, temperatura, peso, altura, null, veterinario, ficha));
        Assertions.assertThrows(NullPointerException.class, () -> new Control(fecha, fechaProximoControl, temperatura, peso, altura, diagnostico, null, ficha));

        // Testing invalid fecha
        log.debug(".. date ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Control(fechaError, fechaProximoControl, temperatura, peso, altura, diagnostico, veterinario, ficha));

        // Testing invalid fecha porximo control
        log.debug("... date next control ...");
        Assertions.assertThrows(RuntimeException.class, () -> new Control(fecha, fechaProximoControl, temperatura, peso, altura, diagnostico, veterinario, ficha));

        // Testing invalid temperatura
        log.debug("... temperature ...");
        Assertions.assertThrows(RuntimeException.class, () -> new Control(fecha, fechaProximoControl, temperaturaError, peso, altura, diagnostico, veterinario, ficha));

        // Testing invalid peso
        log.debug("... weigh ...");
        Assertions.assertThrows(RuntimeException.class, () -> new Control(fecha, fechaProximoControl, temperatura, pesoError, altura, diagnostico, veterinario, ficha));

        // Testing invalid altura
        log.debug("... heigh ...");
        Assertions.assertThrows(RuntimeException.class, () -> new Control(fecha, fechaProximoControl, temperatura, peso, alturaError, diagnostico, veterinario, ficha));

        log.debug("Done.");

    }

}
