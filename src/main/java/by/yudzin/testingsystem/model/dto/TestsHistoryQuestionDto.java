package by.yudzin.testingsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class TestsHistoryQuestionDto implements Serializable {
    QuestionDto question;
    List<TestsUserOptionDto> testsUserOptionEntities;
}