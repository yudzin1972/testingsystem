package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.TestEntity}
 */
@Value
public class TestStartDto implements Serializable {
    Long id;
    String name_test;
    boolean useTimer;
    Integer timerMinutes;
    Integer countQuestion;
}