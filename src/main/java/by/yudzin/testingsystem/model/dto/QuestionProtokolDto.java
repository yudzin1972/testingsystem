package by.yudzin.testingsystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.QuestionEntity}
 */
@Value
public class QuestionProtokolDto implements Serializable {
    Long id;
    @NotBlank
    String text_question;
    String contentQuestion;
    List<OptionProtokolDto> options;
}