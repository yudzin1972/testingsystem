package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestAddDto implements Serializable {

    Long id;
    @NotBlank
    String name_test;
    Float itog;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
    @NotNull
    Long accountId;
    @NotNull
    Long userId;
    List<TestsHistoryQuestionAddDto> questionHistory;
    @NotNull
    Long subjectId;
    Statuses status;
}