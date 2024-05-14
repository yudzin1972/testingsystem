package by.yudzin.testingsystem.service;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.QuestionAddDto;

import java.util.List;

public interface QuestionService {
    List<QuestionAddDto> getQuestionsBySubjectId(Long subjectId) throws RecordNotFountException;

    List<QuestionAddDto> updateAll(List<QuestionAddDto> questionAddDtos) throws RecordAddException;

    void setDeleteStatusNew(Long id) throws RecordNotFountException;
}
