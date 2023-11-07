package gca.technologies.vehicles.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCAPaymentDto {

    /**
     * Hace referencia al identificador de la tabla
     */
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al medio de pago
     */
    @JsonProperty("tipoMedioPago")
    private String paymentType;


    /**
     * Hace referencia al numero de tarjeta
     */
    @JsonProperty("numeroTarjeta")
    @Pattern(regexp = "^[0-9]{16}$", message = "El numero de tarjeta debe tener 16 digitos")
    private String cardNumber;

    /**
     * Hace referencia al codigo de seguridad
     */
    @JsonProperty("cvv")
    @Pattern(regexp = "^[0-9]{3}$", message = "El codigo de seguridad debe tener 3 digitos")
    private String cvv;

    /**
     * Hace referencia a la fecha de expiracion
     */
    @JsonProperty("fechaExpiracion")
    @Pattern(regexp = "^[0-9]{2}/[0-9]{2}$", message = "La fecha de expiracion debe tener el formato MM/YY")
    private String expirationDate;

}
