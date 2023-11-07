package gca.technologies.vehicles.util;

import lombok.Getter;

/**
 * Clase que representa una excepción de negocio
 */
@Getter
public class BussinesException extends RuntimeException {

    /**
     * Codigo de error
     */
    private Integer errorCode;

    /**
     * Servicio error
     */
    private String service;

    /**
     * Constructor de la clase
     *
     * @param message   Hace referencia al mensaje de error
     * @param errorCode Hace referencia al codigo de error
     */
    public BussinesException(String message, Integer errorCode, String service) {
        super(message);
        this.errorCode = errorCode;
        this.service = service;
    }


}
