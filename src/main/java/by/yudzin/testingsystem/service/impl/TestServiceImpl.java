package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.*;
import by.yudzin.testingsystem.model.entity.QuestionEntity;
import by.yudzin.testingsystem.model.entity.TestEntity;
import by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity;
import by.yudzin.testingsystem.model.mapper.*;
import by.yudzin.testingsystem.model.mapper.impl.ProtokolTestMapperImpl;
import by.yudzin.testingsystem.model.repository.QuestionRepository;
import by.yudzin.testingsystem.model.repository.TestRepository;
import by.yudzin.testingsystem.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final TestAddMapper testAddMapper;
    private final TestRunMapper testRunMapper;
    private final TestMapper testMapper;
    private final TestStartMapper testStartMapper;
    private final TestPromRezMapper testPromRezMapper;
    private final TestProtokolMapper testProtokolMapper;
    private final QuestionRepository questionRepository;
    private final ProtokolTestMapperImpl protokolTestMapper;
    private final Constants constants;

    @Transactional
    @Override
    public TestRunDto create(TestAddDto testAddDto) throws RecordAddException {
        try {
            TestEntity testEntity = testAddMapper.toEntity(testAddDto);
            List<QuestionEntity> questionEntityList = questionRepository.getBySubjectIdRandomFixedRecord(testAddDto.getSubjectId(), testAddDto.getCountQuestion());
            List<TestsHistoryQuestionEntity> questionsUser = questionEntityList.stream().map(e -> {
                TestsHistoryQuestionEntity testsHistoryQuestion = new TestsHistoryQuestionEntity();
                testsHistoryQuestion.setQuestion(e);
                testsHistoryQuestion.setStatus(Statuses.NEW);
                return testsHistoryQuestion;
            }).collect(Collectors.toList());
            testEntity.setQuestionHistory(questionsUser);
            return testRunMapper.toDto(testRepository.saveAndFlush(testEntity));
        } catch (Exception e) {
            throw new RecordAddException(constants.ERROR_ADD_RECORD_CONSTANT + "\n" + e.getMessage());
        }
    }

    @Override
    public TestAddDto getOne(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        return testAddMapper.toDto(testEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT)));
    }

    @Override
    public TestStartDto getOneStart(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        return testStartMapper.toDto(testEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT)));
    }

    @Transactional
    @Override
    public void setDeleteStatusTest(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        TestEntity testEntity = testEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
        testEntity.setStatus(Statuses.DEL);
        testRepository.saveAndFlush(testEntity);
    }

    @Transactional
    @Override
    public NextQuestion getNextQuestion(Long id) throws RecordNotFountException {
        List<List> nextQuestionObject = testRepository.getNextQuestion(id);
        NextQuestion nextQuestion = null;
        if (nextQuestionObject != null && !nextQuestionObject.isEmpty()) {
            nextQuestion = new NextQuestion();
            nextQuestion.setId_test(Long.parseLong(nextQuestionObject.get(0).get(0).toString()));
            nextQuestion.setId_historyquestion(Long.parseLong(nextQuestionObject.get(0).get(1).toString()));
            nextQuestion.setId_question(Long.parseLong(nextQuestionObject.get(0).get(2).toString()));
            nextQuestion.setText_question(nextQuestionObject.get(0).get(3).toString());
            nextQuestion.setContent_question(nextQuestionObject.get(0).get(4).toString());
        }
        Optional<TestEntity> testOptional = testRepository.findById(id);
        TestEntity testEntity = testOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
        if (testEntity.getTimeStart() == null) {
            testEntity.setTimeStart(LocalDateTime.now());
            testRepository.saveAndFlush(testEntity);
        }

        return nextQuestion;
    }

    @Override
    public TestPromRezDto getPromRez(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        return testPromRezMapper.toDto(testEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT)));
    }

    @Override
    public TestPromRezDto finish(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        TestEntity testEntity = testEntityOptional.orElseThrow(() -> new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
        testEntity.setStatus(Statuses.DONE);
        testEntity.setTimeFinish(LocalDateTime.now());
        testRepository.saveAndFlush(testEntity);
        return testPromRezMapper.toDto(testEntity);
    }

    @Override
    public List<TestDto> getAll() throws RecordNotFountException {
        List<TestEntity> testEntities = testRepository.findAllByNotStatus(Statuses.DEL.getValue());
        if (testEntities.isEmpty()) {
            throw new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT);
        }
        return testEntities.stream().map(testMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TestProtokolDto getOneProtokol(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        TestEntity testEntity = testEntityOptional.orElseThrow(() -> new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));

        return testProtokolMapper.toDto(testEntity);
    }

    @Override
    public ProtokolTestResponseDto getOneProtokolNew(Long id) throws RecordNotFountException {
        Optional<TestEntity> testEntityOptional = testRepository.findById(id);
        TestEntity testEntity = testEntityOptional.orElseThrow(() -> new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));

        return protokolTestMapper.toDto(testEntity);
    }
}
