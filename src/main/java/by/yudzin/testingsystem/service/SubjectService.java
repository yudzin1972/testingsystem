package by.yudzin.testingsystem.service;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.SubjectAddDto;
import by.yudzin.testingsystem.model.dto.SubjectTestDto;

import java.util.List;

public interface SubjectService {
    SubjectAddDto create(SubjectAddDto subjectAddDto) throws RecordAddException;

    List<SubjectAddDto> updateAll(List<SubjectAddDto> subjectAddDtos) throws RecordAddException;

    SubjectAddDto getOne(Long id) throws RecordNotFountException;

    List<SubjectAddDto> getAllDone() throws RecordNotFountException;

    List<SubjectTestDto> getAllDoneForTest() throws RecordNotFountException;

    void setDeleteStatusNew(Long id) throws RecordNotFountException;

    SubjectAddDto updateOne(SubjectAddDto subjectAddDto) throws RecordAddException, RecordNotFountException;
}
