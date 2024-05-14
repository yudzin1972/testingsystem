package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.QuestionAddDto;
import by.yudzin.testingsystem.model.entity.OptionEntity;
import by.yudzin.testingsystem.model.entity.QuestionEntity;
import by.yudzin.testingsystem.model.mapper.QuestionAddMapper;
import by.yudzin.testingsystem.model.repository.OptionRepository;
import by.yudzin.testingsystem.model.repository.QuestionRepository;
import by.yudzin.testingsystem.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionAddMapper questionAddMapper;
    private final OptionRepository optionRepository;
    private final Constants constants;
    @Override
    public List<QuestionAddDto> getQuestionsBySubjectId(Long subjectId) throws RecordNotFountException {
        return questionRepository.getAllBySubjectId(subjectId).stream().map(questionAddMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionAddDto> updateAll(List<QuestionAddDto> questionAddDtos) throws RecordAddException {
        try {
            List<QuestionEntity> subjects = questionAddDtos.stream().map(e -> {
                if (e.getStatus().getValue().equals("NEW")) {
                    e.setStatus(Statuses.DONE);
                    e.setId(null);
                }
                return questionAddMapper.toEntity(e);
            }).collect(Collectors.toList());
            return questionRepository.saveAll(subjects).stream().map(questionAddMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RecordAddException(constants.ERROR_ADD_RECORD_CONSTANT);
        }
    }

    @Override
    @Transactional
    public void setDeleteStatusNew(Long id) throws RecordNotFountException {
        Optional<QuestionEntity> questionEntityOptional = questionRepository.findById(id);
        QuestionEntity questionEntity = questionEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
        List<OptionEntity> options = optionRepository.getAllByQuestionId(questionEntity.getId());
        List<OptionEntity> newOptions;
        if (!(options == null)) {
            newOptions = options.stream().peek(o -> {
                o.setStatus(Statuses.DEL);
                if(o.getText_opt().isBlank()){
                    o.setText_opt("del");
                }
            }).collect(Collectors.toList());
            questionEntity.setOptions(newOptions);
        }
        questionEntity.setStatus(Statuses.DEL);
        if(questionEntity.getText_question().isBlank()){
            questionEntity.setText_question("del");
        }
        questionRepository.saveAndFlush(questionEntity);
    }

}
