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

import cl.ucn.disc.pdbp.tdd.model.*;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;


public final class ApiRestEndpoints {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ApiRestEndpoints.class);

    /**
     * The Contratos (using SQLite).
     */
    private static final Contratos CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

    /**
     * Private constructor.
     */
    private ApiRestEndpoints(){
        // Nothing here
    }

    /**
     * Ruta 1 GET
     * @param ctx the Javalin {@link Context}.
     */
    public static void getAllFichas(Context ctx){
        log.debug("Getting all the Fichas ..");
        List<Ficha> fichas = CONTRATOS.getAllFichas();
        ctx.json(fichas);
    }

    /**
     * Ruta 1 POST
     * @param ctx the Javalin {@link Context}.
     */
    public static void createFicha(Context ctx){

        log.debug("Creating Ficha ..");

        // Obtain the data for the parameters
        Long numero = Long.parseLong(ctx.queryParam("numero"));
        String nombrePaciente = ctx.queryParam("nombrePaciente");
        String especie = ctx.queryParam("especie");
        String raza = ctx.queryParam("raza");
        Sexo sexo;
        if(ctx.queryParam("sexo").equalsIgnoreCase("hembra")){
            sexo = Sexo.HEMBRA;
        }else{
            sexo = Sexo.MACHO;
        }
        String color = ctx.queryParam("color");
        Tipo tipo;
        if(ctx.queryParam("tipo").equalsIgnoreCase("externo")){
            tipo = Tipo.EXTERNO;
        }else{
            tipo = Tipo.INTERNO;
        }
        // Create the entitys
        Long idDuenio = Long.parseLong(ctx.queryParam("duenio"));
        Persona duenio = CONTRATOS.getPersona(idDuenio);

        Ficha ficha = new Ficha(numero,nombrePaciente,especie,ZonedDateTime.now(),raza,sexo,color,tipo,duenio);
        CONTRATOS.registrarPaciente(ficha);
        ctx.json(ficha);
    }

    /**
     * Ruta 2
     * @param ctx the Javalin {@link Context}.
     */
    public static void findFichas(Context ctx){
        String query = ctx.pathParam("query");
        log.debug("Finding Fichas with query <{}> ..", query);

        List<Ficha> fichas = CONTRATOS.buscarFicha(query);
        ctx.json(fichas);
    }

    /**
     * Ruta 3
     * @param ctx the Javalin {@link Context}.
     */
    public static void getAllPersonas(Context ctx){
        log.debug("Getting all the Personas ..");
        List<Persona> personas = CONTRATOS.getAllPersonas();
        ctx.json(personas);
    }

    /**
     * Ruta 3 POST
     * @param ctx the Javalin {@link Context}.
     */
    public static void createPersona(Context ctx){
        log.debug("Creating Persona ..");
        // Obtain the data for the parameters
        String nombre = ctx.queryParam("nombre");
        String apellido = ctx.queryParam("apellido");
        String rut = ctx.queryParam("rut");
        String direccion = ctx.queryParam("direccion");
        Integer telefonoFijo = Integer.parseInt(ctx.queryParam("telefonoFijo"));
        Integer telefonoMovil = Integer.parseInt(ctx.queryParam("telefonoMovil"));
        String email = ctx.queryParam("email");

        // Create the entitys
        Persona persona = new Persona(nombre, apellido, rut, direccion, telefonoFijo, telefonoMovil, email);

        CONTRATOS.registrarPersona(persona);

        ctx.json(persona);
    }

    /**
     * Ruta 5 GET
     * @param ctx the Javalin {@link Context}.
     */
    public static void getControlesOfFicha(Context ctx){
        log.debug("Finding Controles of Ficha <{}> ..", 1L);

        List<Control> controles = CONTRATOS.getControlesOfFicha(1L);
        ctx.json(controles);
    }


    /**
     * Ruta 5 POST
     * @param ctx the Javalin {@link Context}.
     */
    public static void createControl(Context ctx){

        log.debug("Creating Control ..");

        // Obtain the data for the parameters
        ZonedDateTime fecha = ZonedDateTime.parse(ctx.queryParam("fecha"));
        ZonedDateTime fechaProximoControl = ZonedDateTime.parse(ctx.queryParam("fechaProximoControl"));
        Double temperatura = Double.parseDouble(ctx.queryParam("temperatura"));
        Double peso = Double.parseDouble(ctx.queryParam("peso"));
        Double altura = Double.parseDouble(ctx.queryParam("altura"));
        String diagnostico = ctx.queryParam("diagnostico");

        Long numeroFicha = Long.parseLong(ctx.queryParam("ficha"));
        Ficha ficha = CONTRATOS.getFicha(numeroFicha);
        Long idVeterinario = Long.parseLong(ctx.queryParam("veterinario"));
        Persona veterinario = CONTRATOS.getPersona(idVeterinario);

        // New Control
        Control control = new Control(ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(15),
                temperatura,
                peso,
                altura,
                diagnostico,
                veterinario,
                ficha);
        CONTRATOS.registrarControl(control);
        ctx.json(control);
    }


    /**
     * Ruta 6 GET
     * @param ctx the Javalin {@link Context}.
     */
    public static void getDuenioOfFicha(Context ctx){
        log.debug("Finding Persona of Ficha <{}> ..", 1L);

        Persona persona = CONTRATOS.getDuenioOfFicha(1L);
        ctx.json(persona);
    }

}
