package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

public class TestContratosImpl {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(TestContratosImpl.class);

    /**
     *
     */
    @Test
    public void testRegistrarPaciente() {

        log.debug("Testing Registrar Paciente ..");

        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Crear objeto a ingresar en la ficha
        Persona duenio = new Persona("Andrea",
                "Contreras",
                "152532873",
                "Calle Falsa 123",
                2555562,
                815252522,
                "email.prueba@feik.com");

        // Crear objeto Ficha a ingresar en la BD
        Ficha ficha = new Ficha(123L,
                "Firulais",
                "Canino",
                ZonedDateTime.now(),
                "Pastor Ingles",
                Sexo.MACHO,
                "Negro",
                Tipo.INTERNO,
                duenio);

        ContratosImpl contratos = new ContratosImpl(databaseUrl);

        // Obtener persona de la BD
        Persona duenio2 = contratos.registrarPersona(duenio);

        // Obtener paciente de la BD
        Ficha ficha2 = contratos.registrarPaciente(ficha);
        log.debug("Ficha {}", ficha2);

        // La persona y la ficha no pueden ser null!!
        Assertions.assertNotNull(duenio2,"Persona was null");
        Assertions.assertEquals(duenio2.getId(),duenio.getId(),"Los id no son los mismos");
        Assertions.assertNotNull(ficha2,"Ficha was null");
        Assertions.assertEquals(ficha2.getId(), ficha.getId(),"Los id no son los mismos");
    }

    /**
     *
     */
    @Test
    public void testRegistrarPersona() {

        log.debug("Testing Registrar Persona ..");

        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Crear objeto persona a ingresar en la BD
        Persona persona = new Persona("Ignacio",
                "Santander",
                "193991769",
                "Calle Verdadera 133",
                552771070,
                968332149,
                "ignacio.santander@alumnos.ucn.cl");

        ContratosImpl contratos = new ContratosImpl(databaseUrl);

        // Obtener persona de la BD
        Persona persona2 = contratos.registrarPersona(persona);
        log.debug("Persona {}", persona);

        // La persona no puede ser null!!
        Assertions.assertNotNull(persona2,"Persona was null");
        Assertions.assertEquals(persona2.getId(),persona.getId(),"Los id no son los mismos");

    }

    /**
     *
     */
    @Test
    public void testBuscarFicha() {

        log.debug("Testing Registrar Paciente ..");

        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Crear objeto a ingresar en la ficha
        Persona duenio = new Persona("Felipe",
                "Santander",
                "210674608",
                "Calle Falsa 123",
                552771070,
                949872950,
                "felipesantanderq13@gmail.com");

        // Crear objeto Ficha a ingresar en la BD
        Ficha ficha = new Ficha(124L,
                "Galatea",
                "Canino",
                ZonedDateTime.now(),
                "YorkShire Terrier",
                Sexo.HEMBRA,
                "Negro Dorado",
                Tipo.INTERNO,
                duenio);

        ContratosImpl contratos = new ContratosImpl(databaseUrl);

        // Obtener persona de la BD
        Persona duenio2 = contratos.registrarPersona(duenio);

        // Obtener paciente de la BD
        Ficha ficha2 = contratos.registrarPaciente(ficha);
        //log.debug("Ficha {}", ficha2);

        // La persona y la ficha no pueden ser null!!
        Assertions.assertNotNull(duenio2,"Persona was null");
        Assertions.assertEquals(duenio2.getId(),duenio.getId(),"Los id no son los mismos");
        Assertions.assertNotNull(ficha2,"Ficha was null");
        Assertions.assertEquals(ficha2.getId(), ficha.getId(),"Los id no son los mismos");

        List<Ficha> listaFichas = contratos.buscarFicha(String.valueOf(ficha2.getId()));
        log.debug("Ficha {}", listaFichas);

    }
}