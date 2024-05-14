package by.yudzin.testingsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tests_user_options")
public class TestsUserOptionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_option", nullable = false)
    private Long id_option;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "q_user_id", nullable = false, referencedColumnName = "id")
    private TestsHistoryQuestionEntity qUser;

    @Column(name = "correct", nullable = false)
    private Boolean correct = false;

}