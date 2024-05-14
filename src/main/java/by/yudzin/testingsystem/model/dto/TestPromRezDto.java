package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.TestEntity}
 */
@Value
public class TestPromRezDto implements Serializable {
    Long id;
    String name_test;
    Double itog;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    Integer countQuestion;
    Integer countAnswer;
}