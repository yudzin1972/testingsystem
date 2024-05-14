package by.yudzin.testingsystem.model.dto;

import by.yudzin.testingsystem.enums.Statuses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OptionDto implements Serializable {
    Statuses status;
    Long id;
    String text_opt;
}