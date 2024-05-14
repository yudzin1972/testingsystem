package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.model.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query(value = "SELECT * FROM testingsystem.questions WHERE subject_id =?1 AND STATUS<>'DEL'", nativeQuery = true)
    List<QuestionEntity> getAllBySubjectId(Long subjectId);

    @Query(value = "SELECT * FROM testingsystem.questions WHERE subject_id =?1 AND STATUS<>'DEL' ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<QuestionEntity> getBySubjectIdRandomFixedRecord(Long subjectId, Integer count);

}