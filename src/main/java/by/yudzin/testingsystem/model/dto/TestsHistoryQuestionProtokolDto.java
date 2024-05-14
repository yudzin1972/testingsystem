package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity}
 */
@Value
public class TestsHistoryQuestionProtokolDto implements Serializable {
    Long id;
    Statuses status;
    Boolean correctAnswer;
    QuestionProtokolDto question;
    List<TestsUserOptionProtokolDto> testsUserOptionEntities;
}