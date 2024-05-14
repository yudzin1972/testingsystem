package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class QuestionAddDto implements Serializable {
    Long id;
    @NotBlank
    String text_question;
    Long idSubject;
    String contentQuestion;
    Statuses status;
    List<OptionAddDto> options;
}