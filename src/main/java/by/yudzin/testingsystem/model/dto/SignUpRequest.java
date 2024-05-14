package by.yudzin.testingsystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class SignUpRequest {


    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;

    private String fio;


    @Size(min = 5, max = 255, message = "Длина пароля должна быть от 5 до 255 символов")
    private String password;
}
