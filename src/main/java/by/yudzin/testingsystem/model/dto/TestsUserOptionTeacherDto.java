package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class TestsUserOptionTeacherDto implements Serializable {
    Long id;
    Long id_option;
    Boolean correct;
}