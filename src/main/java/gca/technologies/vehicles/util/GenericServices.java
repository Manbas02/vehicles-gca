package gca.technologies.vehicles.util;

import gca.technologies.vehicles.model.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que contiene metodos genericos para los servicios
 */
@Slf4j
public class GenericServices {

    /**
     * Método encargado de construir las respuestas de los servicios
     * @param code Hace referencia al código de respuesta
     * @param message Hace referencia al mensaje de respuesta
     * @param data Hace referencia a los datos de respuesta
     * @return
     */
    public static Response buildResponse(Integer code, String message, Object data) {
        return Response.builder()
                .responseCode(code)
                .responseMessage(message)
                .data(data)
                .build();
    }

    public static String recoverFormattedDate(){
        LocalDateTime registerDate = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String date = registerDate.format(formato);
        return date;
    }

}
