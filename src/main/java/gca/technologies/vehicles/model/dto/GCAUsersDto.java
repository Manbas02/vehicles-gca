package gca.technologies.vehicles.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GCAUsersDto {

    /**
     * Hace referencia al nombre de usuario
     */
    @JsonProperty("usuario")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El nombre de usuario solo puede contener letras y numeros")
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    private String userName;

    /**
     * Hace referencia al nombre
     */
    @JsonProperty("nombres")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "El nombre solo puede contener letras")
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    /**
     * Hace referencia al apellido
     */
    @JsonProperty("apellidos")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "El apellido solo puede contener letras")
    @NotNull(message = "El apellido no puede ser nulo")
    private String lastName;

    /**
     * Hace referencia a la contraseña
     */
    @JsonProperty("contrasena")
    @NotNull(message = "La contraseña no puede ser nula")
    private String password;

}
