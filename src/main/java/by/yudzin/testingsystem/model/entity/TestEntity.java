package by.yudzin.testingsystem.model.entity;

import by.yudzin.testingsystem.enums.Statuses;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tests")
public class TestEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_test", nullable = false)
    private String name_test;

    @Column(name = "itog")
    private Double itog;

    @Column(name = "time_start")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime timeStart;

    @Column(name = "time_finish")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime timeFinish;

    @Column(name = "use_timer", nullable = false)
    private boolean useTimer;

    @Column(name = "timer_minutes")
    private Integer timerMinutes;

    @Column(name = "count_question")
    private Integer countQuestion;

    @Column(name = "count_answer")
    private Integer countAnswer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    Statuses status;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "test_id")
    private List<TestsHistoryQuestionEntity> questionHistory;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectEntity subject;
}
