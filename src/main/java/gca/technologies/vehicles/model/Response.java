package gca.technologies.vehicles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Clase encargada de manejar la respuesta de los servicios
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonProperty("codigo")
    private Integer responseCode;

    @JsonProperty("mensaje")
    private String responseMessage;

    @JsonProperty("data")
    private Object data;

}
