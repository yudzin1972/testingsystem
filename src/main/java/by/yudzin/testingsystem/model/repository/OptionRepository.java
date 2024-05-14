package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.model.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
    @Query(value = "SELECT * FROM testingsystem.options WHERE question_id =?1 AND STATUS<>'DEL'", nativeQuery = true)
    List<OptionEntity> getAllByQuestionId(Long id);

    List<OptionEntity> findAllById(Long id);
    @Query(value = "SELECT * FROM testingsystem.options WHERE question_id =?1 AND STATUS<>'DEL' order by rand();", nativeQuery = true)
    List<OptionEntity> getAllByQuestionIdOrderRand(Long id);
}