package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Clase Examen
 * @author Ignacio Santander Quiñones
 */
public final class Examen {

    /**
     * ID
     */
    private Long idExamen;

    /**
     * Nombre del Examen
     */
    private String nombreExamen;

    /**
     * Fecha del Examen
     */
    private ZonedDateTime fechaExamen;

    /**
     * Constructor of la clase Examen
     * @param nombreExamen nombre del examen
     * @param fechaExamen fecha del examen
     */
    public Examen(String nombreExamen, ZonedDateTime fechaExamen){
        this.nombreExamen = nombreExamen;
        this.fechaExamen = fechaExamen;
    }
}
