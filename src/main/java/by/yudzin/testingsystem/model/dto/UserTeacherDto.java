package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.UserEntity}
 */
@Value
public class UserTeacherDto implements Serializable {
    Long id;
    String username;
    String fio;
    List<TestTeacherDto> tests;
}