package by.yudzin.testingsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NextQuestion implements Serializable {
    private Long id_test;
    private Long id_historyquestion;
    private Long id_question;
    private String text_question;
    private String content_question;
}
