package by.yudzin.testingsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TestTakeDto implements Serializable {
    Long id;
    String name_test;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
    Long accountId;
    Long subjectId;
}