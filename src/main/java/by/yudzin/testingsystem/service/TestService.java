package by.yudzin.testingsystem.service;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.*;

import java.util.List;

public interface TestService {
    TestRunDto create(TestAddDto testAddDto) throws RecordAddException;
    TestAddDto getOne(Long id) throws RecordNotFountException;
    TestStartDto getOneStart(Long id) throws RecordNotFountException;
    void setDeleteStatusTest(Long id)  throws RecordNotFountException;
    NextQuestion getNextQuestion(Long id) throws RecordNotFountException;
    TestPromRezDto getPromRez(Long id) throws RecordNotFountException;
    TestPromRezDto finish(Long id) throws RecordNotFountException;
    List<TestDto> getAll() throws RecordNotFountException;
    TestProtokolDto getOneProtokol(Long id) throws RecordNotFountException;
    ProtokolTestResponseDto getOneProtokolNew(Long id) throws RecordNotFountException;
}
