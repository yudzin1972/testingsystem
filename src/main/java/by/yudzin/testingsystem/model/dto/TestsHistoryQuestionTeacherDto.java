package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class TestsHistoryQuestionTeacherDto implements Serializable {
    Statuses status;
    Long id;
    Statuses statusquestion;
    Boolean correctAnswer;
    List<TestsUserOptionTeacherDto> testsUserOptionEntities;
}