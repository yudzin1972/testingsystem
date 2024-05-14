package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Role;
import by.yudzin.testingsystem.enums.Statuses;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    String fio;
    Role role;
    Statuses status;
}