package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.SubjectAddDto;
import by.yudzin.testingsystem.model.dto.SubjectTestDto;
import by.yudzin.testingsystem.model.entity.OptionEntity;
import by.yudzin.testingsystem.model.entity.QuestionEntity;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import by.yudzin.testingsystem.model.mapper.SubjectAddMapper;
import by.yudzin.testingsystem.model.mapper.SubjectTestMapper;
import by.yudzin.testingsystem.model.repository.OptionRepository;
import by.yudzin.testingsystem.model.repository.QuestionRepository;
import by.yudzin.testingsystem.model.repository.SubjectRepository;
import by.yudzin.testingsystem.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectAddMapper subjectAddMapper;
    private final SubjectTestMapper subjectTestMapper;
    private final SubjectRepository subjectRepository;
    private final Constants constants;

    private final QuestionRepository questionRepository;

    private final OptionRepository optionRepository;

    @Override
    @Transactional
    public SubjectAddDto create(SubjectAddDto subjectAddDto) throws RecordAddException {
        try {
            SubjectEntity subjectEntity = subjectAddMapper.toEntity(subjectAddDto);
            return subjectAddMapper.toDto(subjectRepository.save(subjectEntity));
        } catch (Exception e) {
            throw new RecordAddException(constants.ERROR_ADD_RECORD_CONSTANT);
        }
    }


    @Override
    @Transactional
    public List<SubjectAddDto> updateAll(List<SubjectAddDto> subjectAddDtos) throws RecordAddException {
        try {
            List<SubjectEntity> subjects = subjectAddDtos.stream().map(e -> {
                if (e.getStatus().getValue().equals("NEW")) {
                    e.setStatus(Statuses.DONE);
                    e.setId(null);
                }
                return subjectAddMapper.toEntity(e);
            }).collect(Collectors.toList());
            return subjectRepository.saveAll(subjects).stream().map(subjectAddMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RecordAddException(constants.ERROR_ADD_RECORD_CONSTANT);
        }
    }

    @Override
    public SubjectAddDto getOne(Long id) throws RecordNotFountException {
        Optional<SubjectEntity> subjectEntityOptional = subjectRepository.findById(id);
        SubjectAddDto subjectAddDto = subjectAddMapper.toDto(subjectEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT)));
        return subjectAddDto;
    }

    @Override
    public List<SubjectAddDto> getAllDone() throws RecordNotFountException {
        List<SubjectAddDto> subjects = subjectRepository.findAllByStatus(Statuses.DONE).stream().map(subjectAddMapper::toDto).collect(Collectors.toList());
        if (!subjects.isEmpty()) {
            return subjects;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<SubjectTestDto> getAllDoneForTest() throws RecordNotFountException {
        List<SubjectTestDto> subjects = subjectRepository.findAllByStatus(Statuses.DONE).stream().map(subjectTestMapper::toDto).collect(Collectors.toList());
        if (!subjects.isEmpty()) {
            return subjects;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void setDeleteStatusNew(Long id) throws RecordNotFountException {
        Optional<SubjectEntity> subjectEntityOptional = subjectRepository.findById(id);
        SubjectEntity subjectEntity = subjectEntityOptional.orElseThrow(() ->
                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
        List<QuestionEntity> questions = questionRepository.getAllBySubjectId(id);
        if (!(questions == null)) {
            List<QuestionEntity> newQuestions = questions.stream().map(q -> {
                List<OptionEntity> options = optionRepository.getAllByQuestionId(q.getId());
                List<OptionEntity> newOptions;
                if (!(options == null)) {
                    newOptions = options.stream().map(o -> {
                        o.setStatus(Statuses.DEL);
                        return o;
                    }).collect(Collectors.toList());
                    q.setOptions(newOptions);
                }
                q.setStatus(Statuses.DEL);
                return q;
            }).collect(Collectors.toList());
            subjectEntity.setQuestions(newQuestions);
            subjectEntity.setStatus(Statuses.DEL);
        }
        subjectRepository.saveAndFlush(subjectEntity);
    }

    @Transactional
    @Override
    public SubjectAddDto updateOne(SubjectAddDto subjectAddDto) throws RecordAddException, RecordNotFountException {
//        Optional<SubjectEntity> subjectEntityOptional = subjectRepository.findById(subjectAddDto.getId());
//        SubjectEntity subjectEntity = subjectEntityOptional.orElseThrow(() ->
//                new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
//        subjectEntity.setNameSubject(subjectAddDto.getNameSubject());
//        subjectEntity.setCountQuestion(subjectAddDto.getCountQuestion());
//        subjectEntity.setTimerMinutes(subjectAddDto.getTimerMinutes());
//        subjectEntity.setUseTimer(subjectAddDto.isUseTimer());
        SubjectEntity subjectEntity=subjectAddMapper.toEntity(subjectAddDto);
        subjectEntity.setStatus(Statuses.DONE);
        return subjectAddMapper.toDto(subjectRepository.saveAndFlush(subjectEntity));
    }
}
