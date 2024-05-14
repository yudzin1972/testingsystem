package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountAdminDto implements Serializable {
    Long id;
    @Email
    String email;
    String password;
    String userName;
    Statuses status;
    @JsonProperty("data")
    List<RoleAdminDto> accountRoles;
}