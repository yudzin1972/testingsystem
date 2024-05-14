package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestDto implements Serializable {
    Long id;
    String name_test;
    Double itog;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
    Integer countAnswer;
    UserDto user;
    List<TestsHistoryQuestionDto> questionHistory;
    SubjectAddDto subject;
    Statuses status;
}