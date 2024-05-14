package by.yudzin.testingsystem.service;

import by.yudzin.testingsystem.exception.DublicatAddException;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.exception.RecordUpdateException;
import by.yudzin.testingsystem.model.dto.AccountAddDto;
import by.yudzin.testingsystem.model.dto.AccountAdminDto;
import by.yudzin.testingsystem.model.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccount() throws RecordNotFountException;

    AccountAddDto saveAccount(AccountAddDto accountAddDto) throws RecordUpdateException;

    AccountDto getAccountByEmail(String email) throws RecordNotFountException;

    AccountAddDto createAccount(AccountAddDto accountAddDto) throws RecordAddException, DublicatAddException;
    List<AccountAdminDto> getAllAccountAdmin() throws RecordNotFountException ;
}
