package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;


@Value
public class TestsUserOptionProtokolDto implements Serializable {
    Long id_option;
    Boolean correct;
}