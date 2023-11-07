package gca.technologies.vehicles.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.*;

/**
 * Clase encargada de mappear la tabla GCA_STATUS
 */
@Entity
@Table(name = "GCA_STATUS", schema = "ADMIN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCAStatusEntity {

    /**
     * Hace referencia al identificador de la tabla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_REGISTER")
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al codigo de estado
     */
    @Column(name = "STATUS_CODE")
    @JsonProperty("codigoEstado")
    private Integer statusCode;

    /**
     * Hace referencia al tipo de estado
     */
    @Column(name = "STATUS_TYPE")
    @JsonProperty("tipoEstado")
    private String statusType;

}
