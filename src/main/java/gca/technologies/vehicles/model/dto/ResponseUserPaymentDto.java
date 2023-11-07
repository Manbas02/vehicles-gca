package gca.technologies.vehicles.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gca.technologies.vehicles.model.entity.GCAPaymentsEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserPaymentDto {

    @JsonProperty("idUsuario")
    private Integer userId;

    @JsonProperty("usuario")
    private String userName;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("apellido")
    private String lastName;

    @JsonProperty("contrasena")
    private String password;

    @JsonProperty("fechaRegistro")
    private String registerDate;

    @JsonProperty("MediosDePago")
    private List<GCAPaymentsEntity> paymentType;

}
