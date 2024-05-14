package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.model.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    @Query(value = "SELECT * FROM testingsystem.tests WHERE testingsystem.tests.id =?1 AND testingsystem.tests.status='New'", nativeQuery = true)
    Optional<TestEntity> getByIdAndNew(Long id);

    @Query(value = "SELECT id_test,id_historyquestion,id_question,text_question,content_question FROM testingsystem.next_question where id_test=?1 order by rand() limit 1", nativeQuery = true)
    List<List> getNextQuestion(Long id);

    @Query(value = "SELECT * FROM testingsystem.tests WHERE testingsystem.tests.status<>'?1'", nativeQuery = true)
    List<TestEntity> findAllByNotStatus(String status);
}