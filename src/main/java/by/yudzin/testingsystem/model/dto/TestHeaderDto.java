package by.yudzin.testingsystem.model.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestHeaderDto implements Serializable {
    String name_test;
    Integer countQuestion;
    Integer countAnswer;
}