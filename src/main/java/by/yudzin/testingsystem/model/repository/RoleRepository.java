package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}