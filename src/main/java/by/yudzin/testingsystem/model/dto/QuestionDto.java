package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.QuestionEntity}
 */
@Value
public class QuestionDto implements Serializable {
    Long id;
    @NotBlank
    String text_question;
    String contentQuestion;
    Long idSubject;
    Statuses status;
    List<OptionDto> options;
}