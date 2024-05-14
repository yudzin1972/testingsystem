package by.yudzin.testingsystem.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    Long id;
    String oldPassword;
    @Size(min = 5, max = 255, message = "Длина пароля должна быть от 5 до 255 символов")
    String password;
}
