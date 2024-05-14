package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.PasswordDataMismatch;
import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.ChangePasswordRequest;
import by.yudzin.testingsystem.model.dto.UserDto;

import by.yudzin.testingsystem.service.impl.AuthenticationService;
import by.yudzin.testingsystem.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping({"/tsystem"})
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @GetMapping({"/user/all"})
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping({"/user/teacherall"})
    public ResponseEntity<?> getTeacherAll() {
        try {
            return ResponseEntity.ok(userService.getTeacherAll());
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping({"/user/{username}"})
    public ResponseEntity<?> getOneByUserName(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.loadUserDtoByUsername(username));
        } catch (UsernameNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping({"/user/all"})
    public ResponseEntity<?> saveAll(@RequestBody List<UserDto> userDtos) {
        try {
            return ResponseEntity.ok(userService.saveAll(userDtos));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping({"/user/changePassword"})
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest userPassDto) {
        try {
            log.info("Пользователю "+userPassDto.getId()+" изменен пароль...");
            return ResponseEntity.ok(authenticationService.changePassword(userPassDto));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (PasswordDataMismatch e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

