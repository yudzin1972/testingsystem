package by.yudzin.testingsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class SubjectEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_subject", nullable = false, length = 128)
    private String nameSubject;

    @Column(name = "use_timer", nullable = false)
    private boolean useTimer;

    @Column(name = "timer_minutes")
    private Integer timerMinutes;

    @Column(name = "count_question")
    private Integer countQuestion;

    @OneToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "subject_id")
    private List<QuestionEntity> questions;
}
