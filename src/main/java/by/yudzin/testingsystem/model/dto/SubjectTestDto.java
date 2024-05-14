package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;


@Value
public class SubjectTestDto implements Serializable {
    Long id;
    String nameSubject;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
}