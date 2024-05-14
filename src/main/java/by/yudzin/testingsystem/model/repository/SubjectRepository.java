package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    List<SubjectEntity> findAllByStatus(Statuses status);
}