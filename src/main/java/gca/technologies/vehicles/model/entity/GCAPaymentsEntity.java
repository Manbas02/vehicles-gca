package gca.technologies.vehicles.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

/**
 * Clase encargada de mappear la tabla GCA_PAYMENTS
 */
@Entity
@Table(name = "GCA_PAYMENTS", schema = "ADMIN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCAPaymentsEntity {

    /**
     * Hace referencia al identificador de la tabla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_REGISTER")
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al medio de pago
     */
    @Column(name = "PAYMENT_TYPE")
    @JsonProperty("medioPago")
    private String paymentType;

    /**
     * Hace referencia al numero de tarjeta
     */
    @Column(name = "CARD_NUMBER")
    @JsonProperty("numeroTarjeta")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cardNumber;

    /**
     * Hace referencia al codigo de seguridad
     */
    @Column(name = "CVV")
    @JsonProperty("cvv")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cvv;

    /**
     * Hace referencia a la fecha de expiracion
     */
    @Column(name = "EXPIRATION_DATE")
    @JsonProperty("fechaExpiracion")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expirationDate;
}
