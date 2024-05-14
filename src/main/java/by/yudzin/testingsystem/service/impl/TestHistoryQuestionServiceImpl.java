package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.TestsHistoryQuestionDoneDto;
import by.yudzin.testingsystem.model.dto.TestsUserOptionDoneDto;
import by.yudzin.testingsystem.model.entity.OptionEntity;
import by.yudzin.testingsystem.model.entity.TestEntity;
import by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity;
import by.yudzin.testingsystem.model.entity.TestsUserOptionEntity;
import by.yudzin.testingsystem.model.mapper.TestsUserOptionDoneMapper;
import by.yudzin.testingsystem.model.repository.TestRepository;
import by.yudzin.testingsystem.model.repository.TestsHistoryQuestionRepository;
import by.yudzin.testingsystem.model.repository.TestsUserOptionRepository;
import by.yudzin.testingsystem.service.TestHistoryQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TestHistoryQuestionServiceImpl implements TestHistoryQuestionService {
    private final TestsHistoryQuestionRepository testsHistoryQuestionRepository;
    private final TestsUserOptionDoneMapper testsUserOptionDoneMapper;
    private final TestsUserOptionRepository testsUserOptionRepository;
    private final TestRepository testRepository;
    private final Constants constants;

    @Transactional
    @Override
    public void setDoneStatus(TestsHistoryQuestionDoneDto testsHistoryQuestionDoneDto) throws RecordNotFountException {
        TestsHistoryQuestionEntity historyQuestionEntity = testsHistoryQuestionRepository.findByQuestion_idAndTest_id(testsHistoryQuestionDoneDto.getId(), testsHistoryQuestionDoneDto.getId_test());
        if (historyQuestionEntity == null) {
            throw new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT);
        }
        historyQuestionEntity.setStatus(testsHistoryQuestionDoneDto.getStatus());
        testsHistoryQuestionRepository.saveAndFlush(historyQuestionEntity);
        List<TestsUserOptionDoneDto> optionsDto = testsHistoryQuestionDoneDto.getTestsUserOptionEntities();
        List<TestsUserOptionEntity> testsUserOptionEntities = optionsDto.stream().map(e -> {
            TestsUserOptionEntity en = testsUserOptionDoneMapper.toEntity(e);
            TestsHistoryQuestionEntity testsHistoryQuestionEntity = new TestsHistoryQuestionEntity();
            testsHistoryQuestionEntity.setId(historyQuestionEntity.getId());
            testsHistoryQuestionEntity.setStatus(Statuses.DONE);

            en.setQUser(testsHistoryQuestionEntity);
            return en;
        }).collect(Collectors.toList());
        List<TestsUserOptionEntity> userOptionEntities = testsUserOptionRepository.saveAllAndFlush(testsUserOptionEntities);
        setResultQuestion(testsHistoryQuestionDoneDto, userOptionEntities, historyQuestionEntity);
    }

    public void setResultQuestion(TestsHistoryQuestionDoneDto testsHistoryQuestionDoneDto, List<TestsUserOptionEntity> userOptionEntities, TestsHistoryQuestionEntity historyQuestionEntity) throws RecordNotFountException {
        int countFinishQuestions;
        Optional<TestEntity> testOptional = testRepository.findById(testsHistoryQuestionDoneDto.getId_test());
        TestEntity testEntity = testOptional.orElseThrow(() -> new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));

        countFinishQuestions = testsHistoryQuestionRepository.getAllByTest_idAndStatus(testsHistoryQuestionDoneDto.getId_test(), "DONE").size();
        // is of correct answers
        List<OptionEntity> options = historyQuestionEntity.getQuestion().getOptions();
        List<TestsUserOptionEntity> userOptions = historyQuestionEntity.getTestsUserOptionEntities();

        int countIncorrect = options.stream().map(e1 -> {
            TestsUserOptionEntity e3 = userOptions.stream().filter(e2 -> e2.getId_option() == e1.getId()).findFirst().orElse(null);
            return e1.isCorrect() == e3.getCorrect();
        }).toList().stream().filter(e -> (!e)).toList().size();


        historyQuestionEntity.setCorrectAnswer(countIncorrect == 0);
        testsHistoryQuestionRepository.saveAndFlush(historyQuestionEntity);
        testEntity.setCountAnswer(countFinishQuestions);
        int countCorrectQuestions =testsHistoryQuestionRepository.getAllByTest_idAndCorrectAnswer(testsHistoryQuestionDoneDto.getId_test()).size();
        double percent = (double) (countCorrectQuestions * 100 / testEntity.getCountQuestion());
        testEntity.setItog(percent);
        testRepository.save(testEntity);
    }
}

