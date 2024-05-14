package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class OptionAddDto implements Serializable {
    Long id;
    @NotBlank
    String text_opt;
    boolean correct;
    Long idQuestion;
    Statuses status;
}