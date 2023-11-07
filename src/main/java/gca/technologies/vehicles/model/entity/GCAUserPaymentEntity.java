package gca.technologies.vehicles.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.*;

/**
 * Clase encargada de mappear la tabla GCA_USER_PAYMENT
 */
@Entity
@Table(name = "GCA_USER_PAYMENT", schema = "ADMIN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCAUserPaymentEntity {

    /**
     * Hace referencia al identificador de la tabla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_REGISTER")
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al usuario
     */
    @ManyToOne
    @JoinColumn(name = "FK_PK_ID_USER")
    @JsonProperty("usuario")
    private GCAUsersEntity user;

    /**
     * Hace referencia al medio de pago
     */
    @ManyToOne
    @JoinColumn(name = "FK_PK_ID_PAYMENT")
    @JsonProperty("medioPago")
    private GCAPaymentsEntity paymentType;

}
