package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.TestAddDto;
import by.yudzin.testingsystem.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tsystem")
@RequiredArgsConstructor
@Slf4j
public class TestRestController {
    private final TestService testService;

    @PostMapping("/test/addtest")
    public ResponseEntity<?> create(@Valid @RequestBody TestAddDto testAddDto) {
        try {
            return ResponseEntity.ok(testService.create(testAddDto));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test/setdelete/{id}")
    public ResponseEntity<?> setDeleteTest(@PathVariable Long id) {
        try {
            testService.setDeleteStatusTest(id);
            return ResponseEntity.ok("Ok");
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test/nextquestion/{id}")
    public ResponseEntity<?> getNextQuestion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(testService.getNextQuestion(id));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test/{id}/finish")
    public ResponseEntity<?> finish(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(testService.finish(id));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/test/{id}/promrez")
    public ResponseEntity<?> getPromRez(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(testService.getPromRez(id));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test/all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(testService.getAll());
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test/{id}/protokol")
    public ResponseEntity<?> getOneProtokol(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(testService.getOneProtokol(id));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test/{id}/protokolnew")
    public ResponseEntity<?> getOneProtokolNew(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(testService.getOneProtokolNew(id).getQuestions());
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test/{id}/getforadd")
    public ResponseEntity<?> getforadd(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(testService.getOne(id));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
