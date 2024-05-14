package by.yudzin.testingsystem.model.entity;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "text_question", nullable = false)
    private String text_question;

    @Column(name = "content_question", columnDefinition = "mediumtext")
    private String contentQuestion;

    @Column(name = "subject_id")
    private Long idSubject;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    Statuses status;

    @OneToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id")
    private List<OptionEntity> options;
}
