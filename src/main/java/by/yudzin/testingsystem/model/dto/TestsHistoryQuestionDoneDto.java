package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestsHistoryQuestionDoneDto implements Serializable {
    Long id;
    Long id_test;
    Statuses status;
    List<TestsUserOptionDoneDto> testsUserOptionEntities;
}