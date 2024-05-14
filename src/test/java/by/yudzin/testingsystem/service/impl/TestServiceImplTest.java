package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.*;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import by.yudzin.testingsystem.model.entity.TestEntity;
import by.yudzin.testingsystem.model.entity.UserEntity;
import by.yudzin.testingsystem.model.mapper.*;
import by.yudzin.testingsystem.model.mapper.impl.ProtokolTestMapperImpl;
import by.yudzin.testingsystem.model.repository.QuestionRepository;
import by.yudzin.testingsystem.model.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private TestAddMapper testAddMapper;

    @Mock
    private TestRunMapper testRunMapper;
    @Mock
    private Constants constants;
    @Mock
    private TestMapper testMapper;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestStartMapper testStartMapper;
    @Mock
    private TestProtokolMapper testProtokolMapper;
    @Mock
    private TestPromRezMapper testPromRezMapper;
    @Mock
    private ProtokolTestMapperImpl protokolTestMapper;

    @InjectMocks
    private TestServiceImpl testService;
    @Test
    void testCreate() throws RecordAddException {
        TestAddDto testAddDto = new TestAddDto();
        TestEntity testEntity = new TestEntity();

        when(testAddMapper.toEntity(testAddDto)).thenReturn(testEntity);
        when(testRepository.saveAndFlush(testEntity)).thenReturn(testEntity);

        TestRunDto testRunDto = testService.create(testAddDto);
        verify(testRepository, times(1)).saveAndFlush(testEntity);
    }
    @Test
    void testGetOneStart() throws RecordNotFountException {
        Long testId = 1L;
        TestEntity testEntity = createTestEntity();

        when(testRepository.findById(testId)).thenReturn(Optional.of(testEntity));

        assertDoesNotThrow(() -> testService.getOneStart(testId));
    }
    @Test
    void testFinish() throws RecordNotFountException {
        Long testId = 1L;
        TestEntity testEntity = createTestEntity();

        when(testRepository.findById(testId)).thenReturn(Optional.of(testEntity));
        TestPromRezDto testPromRezDto = testService.finish(testId);
        testPromRezMapper.toEntity(testPromRezDto);
        verify(testRepository).saveAndFlush(any());
    }

    @Test
    void testGetAll() throws RecordNotFountException {
        List<TestEntity> testEntities = Collections.singletonList(new TestEntity());

        when(testRepository.findAllByNotStatus(Statuses.DEL.getValue())).thenReturn(testEntities);

        List<TestDto> testDtos = testService.getAll();

        assertFalse(testDtos.isEmpty());
        assertEquals(1, testDtos.size());
        verify(testMapper, times(1)).toDto(any());
    }


    @Test
    void testGetOneProtokolNew() throws RecordNotFountException {
        Long testId = 1L;
        TestEntity testEntity = createTestEntity();

        when(testRepository.findById(testId)).thenReturn(Optional.of(testEntity));

        ProtokolTestResponseDto protokolTestResponseDto = testService.getOneProtokolNew(testId);
        verify(protokolTestMapper, times(1)).toDto(any());
    }

    private TestEntity createTestEntity() {
        return TestEntity.builder()
                .name_test("test")
                .countAnswer(0)
                .countQuestion(5)
                .itog(0D)
                .useTimer(true)
                .id(1L)
                .timerMinutes(5)
                .user(new UserEntity())
                .subject(new SubjectEntity())
                .questionHistory(new ArrayList<>())
                .build();
    }

}
