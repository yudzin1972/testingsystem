package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestsHistoryQuestionRepository extends JpaRepository<TestsHistoryQuestionEntity, Long> {
    @Query(value = "SELECT * FROM testingsystem.tests_history_questions where question_id=?1 and test_id=?2", nativeQuery = true)
    TestsHistoryQuestionEntity findByQuestion_idAndTest_id(Long id, Long id_test);
    @Query(value = "SELECT * FROM testingsystem.tests_history_questions where test_id=?1 and testingsystem.tests_history_questions.status=?2", nativeQuery = true)
    List<TestsHistoryQuestionEntity> getAllByTest_idAndStatus(Long id, String status);
    @Query(value = "SELECT * FROM testingsystem.tests_history_questions where test_id=?1 and testingsystem.tests_history_questions.correct_answer=true", nativeQuery = true)
    List<TestsHistoryQuestionEntity> getAllByTest_idAndCorrectAnswer (Long id);
}