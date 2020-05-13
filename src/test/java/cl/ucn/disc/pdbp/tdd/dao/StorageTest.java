
package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Storage Test
 * @author Ignacio Santander Quiñones.
 */
public final class StorageTest {


    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing de ORMLite + H2 (database)
     */
    @Test
    public void testDatabase() throws SQLException {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Connection source: autoclose with the try/catch
        try (ConnectionSource conectionSource = new JdbcConnectionSource(databaseUrl)){

            // Create the table from Persona annotations
            TableUtils.createTableIfNotExists(conectionSource, Persona.class);

            // The dao of Persona
            Dao<Persona,Long> daoPersona = DaoManager.createDao(conectionSource,Persona.class);

            // New Persona
            Persona persona = new Persona("Andrea","Contreras","152532873","Calle Falsa 123", 5895555,54888,"andrea.contreras@feik.com");

            // Insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("Id: {}",persona.getId());
            //
            Assertions.assertEquals(1,tuples,"Save tuples != 1");


            // Get from db
            Persona personaDb = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(),personaDb.getNombre(), "Nombre not equals!");
            Assertions.assertEquals(persona.getApellido(),personaDb.getApellido(),"Apellido not equals!");
            Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"Rut not equals!");

            // Search by rut: SELECT * FROM 'persona' WHERE 'rut' = '152532873'
            List<Persona> personaList = daoPersona.queryForEq("rut","152532873");
            Assertions.assertEquals(1,personaList.size(),"More than one person?!");

            // Not found by rut
            Assertions.assertEquals(0,daoPersona.queryForEq("rut", "19").size(), "Found something !?");

        }catch (IOException e){
            log.error("Error", e);
        }
    }



}
