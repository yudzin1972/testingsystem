package by.yudzin.testingsystem.service;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.OptionAddDto;
import by.yudzin.testingsystem.model.dto.OptionNextDto;

import java.util.List;

public interface OptionService {
    List<OptionAddDto> updateAll(List<OptionAddDto> optionAddDtos) throws RecordAddException;

    List<OptionAddDto> getOptionsByQuestionId(Long questionId) throws RecordNotFountException;

    void setDeleteStatusNew(Long id)  throws RecordNotFountException;

    List<OptionNextDto> getOptionsByQuestionIdOrderRand(Long questionId);
}
