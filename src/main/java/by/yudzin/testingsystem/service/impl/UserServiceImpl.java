package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.exception.DublicatAddException;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.UserDto;
import by.yudzin.testingsystem.model.dto.UserTeacherDto;
import by.yudzin.testingsystem.model.entity.UserEntity;
import by.yudzin.testingsystem.model.mapper.UserMapper;
import by.yudzin.testingsystem.model.mapper.UserTeacherMapper;
import by.yudzin.testingsystem.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    private final Constants constants;
    private final UserMapper userMapper;
    private final UserTeacherMapper userTeacherMapper;

    @Transactional
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Transactional
    public List<UserDto> saveAll(List<UserDto> users) throws RecordAddException {
        List<UserEntity> usersEntity = users.stream().map(e->{
            UserEntity uE = repository.findById(e.getId()).orElseThrow(() -> new UsernameNotFoundException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
            uE.setFio(e.getFio());
            uE.setUsername(e.getUsername());
            uE.setRole(e.getRole());
            return uE;
        }).collect(Collectors.toList());
        List<UserEntity> usersEntitysaved = repository.saveAllAndFlush(usersEntity);
        return usersEntitysaved.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public List<UserDto> findAll() throws RecordNotFountException {
        List<UserEntity> usersEntity = repository.findAll();
        return usersEntity.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
    public List<UserTeacherDto> getTeacherAll() throws RecordNotFountException {
        List<UserEntity> usersEntity = repository.findAll();
        return usersEntity.stream().map(userTeacherMapper::toDto).collect(Collectors.toList());
    }
    @Transactional
    public UserEntity create(UserEntity user) throws DublicatAddException {
        if (repository.existsByUsername(user.getUsername())) {
            throw new DublicatAddException(constants.ERROR_DUBLICAT_EMAIL_CONSTANT);
        }

        return save(user);
    }

    public UserEntity getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserEntity getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public UserDto loadUserDtoByUsername(String username) throws UsernameNotFoundException {

        return userMapper.toDto(repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
    }
}
