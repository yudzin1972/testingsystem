package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class AccountAddDto implements Serializable {
    Long id;
    @Email
    @NotBlank
    String email;
    @NotBlank
    String password;
    String userName;
    Statuses status;
    List<RoleAddAccountDto> accountRoles;
}