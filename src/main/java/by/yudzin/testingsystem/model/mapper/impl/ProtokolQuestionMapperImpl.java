package by.yudzin.testingsystem.model.mapper.impl;

import by.yudzin.testingsystem.model.dto.ProtokolOptionResponseDto;
import by.yudzin.testingsystem.model.dto.ProtokolQuestionResponseDto;
import by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity;
import by.yudzin.testingsystem.model.entity.TestsUserOptionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProtokolQuestionMapperImpl {
    public ProtokolQuestionResponseDto toDto(TestsHistoryQuestionEntity entity){
        List<TestsUserOptionEntity> optionsUser = entity.getTestsUserOptionEntities();
        List<ProtokolOptionResponseDto> options = entity.getQuestion().getOptions().stream().map(e -> {
            ProtokolOptionResponseDto protokolOptionResponseDto = new ProtokolOptionResponseDto();
            TestsUserOptionEntity optionUser = optionsUser.stream().filter(o -> o.getId_option().equals(e.getId())).findFirst().orElse(new TestsUserOptionEntity());
            protokolOptionResponseDto.setId_option(e.getId());
            protokolOptionResponseDto.setText(e.getText_opt());
            protokolOptionResponseDto.setCorrect_option((e.isCorrect()) ? "Да":"Нет");
            protokolOptionResponseDto.setCorrect_option_user((optionUser.getCorrect()) ? "Да":"Нет");
            return protokolOptionResponseDto;
        }).collect(Collectors.toList());

        return ProtokolQuestionResponseDto.builder()
                .id_question_history(entity.getId())
                .id_question(entity.getQuestion().getId())
                .text(entity.getQuestion().getText_question())
                .content_question(entity.getQuestion().getContentQuestion())
                .correctAnswer((entity.getCorrectAnswer())? "Верно":"Не верно")
                .statusQuestionHistory((entity.getStatus().getValue().equals("DONE")) ? "Отвечали":"Не отвечали" )
                .data(options)
                .build();
    }
}
