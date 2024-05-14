package by.yudzin.testingsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestsHistoryQuestionAddDto implements Serializable {
    Long id;
    Long questionId;
    List<TestsUserOptionAddDto> testsUserOptionEntities;
}