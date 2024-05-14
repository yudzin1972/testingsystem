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
public class SubjectAddDto implements Serializable {
    Long id;
    Statuses status;
    @NotBlank
    String nameSubject;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
    List<QuestionAddDto> questions;
}