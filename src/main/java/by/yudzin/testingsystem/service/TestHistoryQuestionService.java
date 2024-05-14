package by.yudzin.testingsystem.service;

import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.TestsHistoryQuestionDoneDto;
import by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity;
import by.yudzin.testingsystem.model.entity.TestsUserOptionEntity;

import java.util.List;

public interface TestHistoryQuestionService {
    void setDoneStatus(TestsHistoryQuestionDoneDto testsHistoryQuestionDoneDto) throws RecordNotFountException;
    void setResultQuestion(TestsHistoryQuestionDoneDto testsHistoryQuestionDoneDto, List<TestsUserOptionEntity> userOptionEntities, TestsHistoryQuestionEntity historyQuestionEntity) throws RecordNotFountException;
}
