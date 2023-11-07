package gca.technologies.vehicles.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentDto {

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
     * Hace referencia a la fecha de inicio de la renta
     */
    @JsonProperty("fechaInicioRenta")
    private String rentStartDate;

    /**
     * Hace referencia a la fecha de fin de la renta
     */
    @JsonProperty("fechaFinRenta")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rentEndDate;

    /**
     * Hace referencia al medio de pago
     */
    @JsonProperty("medioDePago")
    private ResponseUserPaymentDto userPayment;

    /**
     * Hace referencia al estado
     */
    @JsonProperty("estado")
    private String status;

}
