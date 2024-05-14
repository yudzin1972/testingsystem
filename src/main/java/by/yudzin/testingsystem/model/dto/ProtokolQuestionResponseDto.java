package by.yudzin.testingsystem.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProtokolQuestionResponseDto {
    private Long id_question_history;
    private Long id_question;
    private String text;
    private String content_question;
    private String statusQuestionHistory;
    private String correctAnswer;
    private List<ProtokolOptionResponseDto> data;
}
