package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleAdminDto implements Serializable {
    @JsonProperty("idrole")
    Long id;
    String nameRole;
    Statuses status;
}