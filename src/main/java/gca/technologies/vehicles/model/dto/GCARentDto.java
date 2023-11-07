package gca.technologies.vehicles.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCARentDto {

    /**
     * Hace referencia al identificador de la tabla
     */
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al valor
     */
    @JsonProperty("valor")
    private Integer value;

    /**
     * Hace referencia al vehiculo
     */
    @JsonProperty("vehiculo")
    private String vehicle;

    /**
     * Hace referencia al medio de pago
     */
    @JsonProperty("medioDePago")
    private Integer userPayment;

    @JsonProperty("idEstado")
    private Integer statusId;

}
