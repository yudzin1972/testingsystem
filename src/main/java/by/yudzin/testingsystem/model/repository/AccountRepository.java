package by.yudzin.testingsystem.model.repository;

import by.yudzin.testingsystem.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByEmail(String email);

    List<AccountEntity> findAllByEmail(String email);
}