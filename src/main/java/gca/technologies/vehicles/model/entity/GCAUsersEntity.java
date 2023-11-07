package gca.technologies.vehicles.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.*;

/**
 * Clase encargada de mapear la tabla GCA_USERS
 */
@Entity
@Table(name = "GCA_USERS", schema = "ADMIN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCAUsersEntity {

    /**
     * Hace referencia al identificador de la tabla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_REGISTER")
    @JsonProperty("idRegistro")
    private Integer registerId;

    /**
     * Hace referencia al nombre de usuario
     */
    @Column(name = "USER_NAME")
    @JsonProperty("usuario")
    private String userName;

    /**
     * Hace referencia al nombre
     */
    @Column(name = "NAME")
    @JsonProperty("nombres")
    private String name;

    /**
     * Hace referencia al apellido
     */
    @Column(name = "LAST_NAME")
    @JsonProperty("apellidos")
    private String lastName;

    /**
     * Hace referencia a la contraseña
     */
    @Column(name = "PASSWORD")
    @JsonProperty("contrasena")
    private String password;

    /**
     * Hace referencia a la fecha de registro
     */
    @Column(name = "REGISTER_DATE")
    @JsonProperty("fechaRegistro")
    private String registerDate;

}
