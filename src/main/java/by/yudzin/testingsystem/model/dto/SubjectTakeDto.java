package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.SubjectEntity}
 */
@Value
public class SubjectTakeDto implements Serializable {
    Long id;
    String nameSubject;
}