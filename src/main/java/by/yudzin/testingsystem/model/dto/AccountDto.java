package by.yudzin.testingsystem.model.dto;

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
public class AccountDto implements Serializable {
    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String userName;

    private List<RoleDto> accountRoles;
}