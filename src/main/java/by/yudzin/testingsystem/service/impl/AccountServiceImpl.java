package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Statuses;
import by.yudzin.testingsystem.exception.DublicatAddException;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.exception.RecordUpdateException;
import by.yudzin.testingsystem.model.dto.AccountAddDto;
import by.yudzin.testingsystem.model.dto.AccountAdminDto;
import by.yudzin.testingsystem.model.dto.AccountDto;
import by.yudzin.testingsystem.model.entity.AccountEntity;
import by.yudzin.testingsystem.model.entity.RoleEntity;
import by.yudzin.testingsystem.model.mapper.AccountAddMapper;
import by.yudzin.testingsystem.model.mapper.AccountAdminMapper;
import by.yudzin.testingsystem.model.mapper.AccountMapper;
import by.yudzin.testingsystem.model.repository.AccountRepository;
import by.yudzin.testingsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountAddMapper accountAddMapper;
    private final AccountAdminMapper accountAdminMapper;
    private final Constants constants;

    @Override
    public List<AccountDto> getAllAccount() throws RecordNotFountException {
        List<AccountDto> accounts = accountRepository.findAll().stream().map(accountMapper::toDto).collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            return accounts;
        } else {
            throw new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT);
        }

    }

    @Transactional
    @Override
    public AccountAddDto saveAccount(AccountAddDto accountAddDto) throws RecordUpdateException {
        try {
            return accountAddMapper.toDto(accountRepository.saveAndFlush(accountAddMapper.toEntity(accountAddDto)));
        } catch (Exception e) {
            throw new RecordUpdateException(constants.ERROR_UPDATE_RECORD_CONSTANT);
        }

    }

    @Override
    public AccountDto getAccountByEmail(String email) throws RecordNotFountException {
        AccountDto accountDto = accountMapper.toDto(accountRepository.findByEmail(email));
        if (accountDto == null) {
            throw new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT);
        } else {
            return accountDto;
        }
    }

    @Transactional
    @Override
    public AccountAddDto createAccount(AccountAddDto accountAddDto) throws RecordAddException, DublicatAddException {
        List<AccountEntity> allByEmail = accountRepository.findAllByEmail(accountAddDto.getEmail());
        if(!allByEmail.isEmpty()){
            throw new DublicatAddException(constants.ERROR_DUBLICAT_EMAIL_CONSTANT);
        }
        try {
            AccountEntity accountEntity = accountAddMapper.toEntity(accountAddDto);
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(3L);
            roleEntity.setStatus(Statuses.DONE);
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(roleEntity);
            accountEntity.setAccountRoles(roles);
            accountEntity.setStatus(Statuses.DONE);
            return accountAddMapper.toDto(accountRepository.saveAndFlush(accountEntity));
        } catch (Exception e) {
            throw new RecordAddException(constants.ERROR_ADD_RECORD_CONSTANT);
        }
    }

    @Override
    public List<AccountAdminDto> getAllAccountAdmin() throws RecordNotFountException {
        List<AccountAdminDto> accounts = accountRepository.findAll().stream().map(accountAdminMapper::toDto).collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            return accounts;
        } else {
            throw new RecordNotFountException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT);
        }
    }

}
