package gca.technologies.vehicles.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.*;

/**
 * Clase encargada de mappear la tabla GCA_RENT
 */
@Entity
@Table(name = "GCA_RENT", schema = "ADMIN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCARentEntity {

    /**
     * Hace referencia al identificador de la tabla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_REGISTER")
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al valor
     */
    @Column(name = "VALUE")
    @JsonProperty("valor")
    private Integer value;

    /**
     * Hace referencia al vehiculo
     */
    @Column(name = "VEHICLE")
    @JsonProperty("vehiculo")
    private String vehicle;

    /**
     * Hace referencia a la fecha de inicio de la renta
     */
    @Column(name = "RENT_START_DATE")
    @JsonProperty("fechaInicioRenta")
    private String rentStartDate;

    /**
     * Hace referencia a la fecha de fin de la renta
     */
    @Column(name = "RENT_END_DATE")
    @JsonProperty("fechaFinRenta")
    private String rentEndDate;

    /**
     * Hace referencia al medio de pago
     */
    @OneToOne
    @JoinColumn(name = "FK_PK_ID_USER_PAYMENT")
    @JsonProperty("medioDePago")
    private GCAUserPaymentEntity userPayment;

    /**
     * Hace referencia al estado
     */
    @OneToOne
    @JoinColumn(name = "FK_PK_ID_STATUS")
    @JsonProperty("estado")
    private GCAStatusEntity status;

}
