package cl.ucn.disc.pdbp.tdd.model;

import java.time.ZonedDateTime;

/**
 * Clase Control
 * @author Ignacio Santander Quiñones.
 */
public final class Control {

    private Long idControl;

    private ZonedDateTime fecha;

    private ZonedDateTime fechaProximoControl;

    private Double temperatura;

    private Double peso;

    private Double altura;

    private String diagnostico;

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
