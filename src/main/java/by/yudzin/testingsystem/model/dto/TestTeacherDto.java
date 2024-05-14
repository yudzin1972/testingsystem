package by.yudzin.testingsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class TestTeacherDto implements Serializable {
    Long id;
    String name_test;
    Double itog;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime timeStart;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime timeFinish;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
    Integer countAnswer;
    List<TestsHistoryQuestionTeacherDto> questionHistory;
    String subjectNameSubject;
}