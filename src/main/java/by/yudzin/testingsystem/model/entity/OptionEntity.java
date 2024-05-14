package by.yudzin.testingsystem.model.entity;

import by.yudzin.testingsystem.enums.Statuses;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Table(name = "options")
public class OptionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text_opt", nullable = false)
    private String text_opt;

    @Column(name = "correct", nullable = false)
    private boolean correct;

    @Column(name = "question_id")
    private Long idQuestion;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    Statuses status;
}
