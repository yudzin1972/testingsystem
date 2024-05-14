package by.yudzin.testingsystem.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProtokolTestResponseDto {
    private Long id_test;
    private Long id_user;
    private String name_test;
    private Double itog;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;
    boolean useTimer;
    private Integer timerMinutes;
    private Integer countQuestion;
    private Integer countAnswer;
    private List<ProtokolQuestionResponseDto> questions;
}
