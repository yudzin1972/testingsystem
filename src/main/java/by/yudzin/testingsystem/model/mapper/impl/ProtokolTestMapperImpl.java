package by.yudzin.testingsystem.model.mapper.impl;

import by.yudzin.testingsystem.model.dto.ProtokolQuestionResponseDto;
import by.yudzin.testingsystem.model.dto.ProtokolTestResponseDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProtokolTestMapperImpl {
    private final ProtokolQuestionMapperImpl protokolQuestionMapper;

    public ProtokolTestResponseDto toDto(TestEntity entity) {

        List<ProtokolQuestionResponseDto> questions = entity.getQuestionHistory().stream().map(protokolQuestionMapper::toDto).toList();

       return ProtokolTestResponseDto.builder()
                .id_test(entity.getId())
                .id_user(entity.getUser().getId())
                .name_test(entity.getName_test())
                .itog(entity.getItog())
                .timeStart(entity.getTimeStart())
                .timeFinish(entity.getTimeFinish())
                .useTimer(entity.isUseTimer())
                .timerMinutes(entity.getTimerMinutes())
                .countQuestion(entity.getCountQuestion())
                .countAnswer(entity.getCountAnswer())
                .questions(questions)
                .build();
    }
}
