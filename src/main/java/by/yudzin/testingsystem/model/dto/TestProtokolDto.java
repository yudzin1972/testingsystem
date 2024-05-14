package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class TestProtokolDto implements Serializable {
    Long id;
    String name_test;
    Double itog;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
    Integer countAnswer;
    List<TestsHistoryQuestionProtokolDto> questionHistory;
}