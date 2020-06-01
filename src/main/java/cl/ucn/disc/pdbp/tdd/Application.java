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

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *  The Main Aplication.
 * @author Ignacio Santander Quiñones
 */
public final class Application {

    /**
     *  The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Private Contructor.
     */
    private Application(){
        // Nothing.
    }

    /**
     * The Main entry.
     * @param args from console.
     */
    public static void main(String[] args){

        // Gson configuration
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);

        // The Javalin application
        log.debug("Starting Javalin ..");
        Javalin javalin = Javalin.create(javalinConfig -> {

            // enable extensive development logging for http and websocket
            javalinConfig.enableDevLogging();

            // Measure the time
            javalinConfig.requestLogger(((ctx, executionTimeMs) -> {
                log.info("Server {} in {} ms.", ctx.fullUrl(), executionTimeMs);
                ctx.header("Server-Timing", "total;dur=" + executionTimeMs);
            }));

            // Enable routes helper
            javalinConfig.registerPlugin(new RouteOverviewPlugin("/routes"));
        // Define the routes
        }).routes(() -> {

            // The version
            ApiBuilder.path("v1", () -> {

                // /fichas
                ApiBuilder.path("fichas", () -> {

                    // GET -> /fichas
                    ApiBuilder.get(ApiRestEndpoints::getAllFichas);

                    // POST -> /fichas
                    ApiBuilder.post(ApiRestEndpoints::createFicha);

                    // GET -> /fichas/find/{query}
                    ApiBuilder.path("find/:query",()->{
                        ApiBuilder.get(ApiRestEndpoints::findFichas);
                    });

                    // /fichas/{numeroFicha}/controles
                    ApiBuilder.path(":numeroFicha/controles",()->{

                        // POST -> /fichas/{numeroFicha}/controles
                        ApiBuilder.post(ApiRestEndpoints::createControl);

                        // GET -> /fichas/{numeroFicha}/controles
                        ApiBuilder.get(ApiRestEndpoints::getControlesOfFicha);

                    });

                    // /fichas/{numeroFicha}/persona
                    ApiBuilder.path(":numeroFicha/persona",()->{

                        // GET -> /fichas/{numeroFicha}/persona
                        ApiBuilder.get(ApiRestEndpoints::getDuenioOfFicha);

                    });
                });

                // /personas
                ApiBuilder.path("personas",()->{

                    // GET -> /personas
                    ApiBuilder.get(ApiRestEndpoints::getAllPersonas);

                    // POST -> /personas
                    ApiBuilder.post(ApiRestEndpoints::createPersona);

                    // GET -> /personas/?/pageSize={size}&page={number}

                });
            });
            // Start the server at port 7000
        }).start(7000);

        // Sutdown hook!
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                log.debug("Stopping the server ..");
                javalin.stop();
                log.debug("The end.");
        }));

        // A simple route to show time
        javalin.get("/", ctx -> {
            // Show the date.
            ctx.result("The Date: "+ ZonedDateTime.now());
        });


        // Persona
        javalin.get("/personasTest/",ctx -> {

            // Create simply list
            List<Persona> personas = Arrays.asList(
                    new Persona("Diego",
                            "Urrutia",
                            "132204810",
                            "Calle Falsa 999",
                            8985665,
                            99999999,
                            "correo.feik@ucn.cl"),
                    new Persona("Andrea",
                            "Contreras",
                            "152532873",
                            "Calle Falsa 998",
                            88562615,
                            51584899,
                            "corre.feik2@gmail.com"
                            )
            );
            // Send the List
            ctx.json(personas);
        });

    }

}
