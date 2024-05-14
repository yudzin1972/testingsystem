package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.OptionAddDto;
import by.yudzin.testingsystem.model.dto.OptionNextDto;
import by.yudzin.testingsystem.model.entity.OptionEntity;
import by.yudzin.testingsystem.model.mapper.OptionAddMapper;
import by.yudzin.testingsystem.model.mapper.OptionNextMapper;
import by.yudzin.testingsystem.model.repository.OptionRepository;
import by.yudzin.testingsystem.service.OptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final OptionAddMapper optionAddMapper;
    private final OptionNextMapper optionNextMapper;
    private final Constants constants;

    @Override
    @Transactional
    public List<OptionAddDto> updateAll(List<OptionAddDto> optionAddDtos) throws RecordAddException {
        try {
            List<OptionEntity> options = optionAddDtos.stream().map(e -> {
                if (e.getStatus().getValue().equals("NEW")) {
                    e.setStatus(Statuses.DONE);
                    e.setId(null);
                }
                return optionAddMapper.toEntity(e);
            }).collect(Collectors.toList());
            return optionRepository.saveAllAndFlush(options).stream().map(optionAddMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RecordAddException(constants.ERROR_ADD_RECORD_CONSTANT);
        }
    }

    @Override
    public List<OptionAddDto> getOptionsByQuestionId(Long questionId) {
        return optionRepository.getAllByQuestionId(questionId).stream().map(optionAddMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setDeleteStatusNew(Long id) throws RecordNotFountException {
        List<OptionEntity> options = optionRepository.findAllById(id);
        List<OptionEntity> newOptions;
        if (!(options == null)) {
            newOptions = options.stream().peek(o -> {
                o.setStatus(Statuses.DEL);
                if(o.getText_opt().isBlank()){
                    o.setText_opt("del");
                }
            }).collect(Collectors.toList());
            optionRepository.saveAllAndFlush(newOptions);
        }
    }

    @Override
    public List<OptionNextDto> getOptionsByQuestionIdOrderRand(Long questionId) {
        return optionRepository.getAllByQuestionIdOrderRand(questionId).stream().map(optionNextMapper::toDto).collect(Collectors.toList());
    }
}
