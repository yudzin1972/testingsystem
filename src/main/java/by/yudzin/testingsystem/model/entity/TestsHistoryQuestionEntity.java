package by.yudzin.testingsystem.model.entity;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tests_history_questions")
public class TestsHistoryQuestionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    Statuses status;

    @Column(name = "correct_answer")
    Boolean correctAnswer = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @OneToMany(mappedBy = "qUser", cascade = CascadeType.ALL)
    private List<TestsUserOptionEntity> testsUserOptionEntities;

}