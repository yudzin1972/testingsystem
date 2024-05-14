package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;

import by.yudzin.testingsystem.model.dto.SubjectAddDto;
import by.yudzin.testingsystem.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Validated  // for check parameters methods
@RestController
@RequestMapping("/tsystem")
@RequiredArgsConstructor
@Slf4j
public class SubjectRestController {

    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllDone() {
        try {
            return ResponseEntity.ok(subjectService.getAllDone());
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body("Ошибка " + e.getMessage());
        }
    }

    @GetMapping("/subjects/fortest")
    public ResponseEntity<?> getAllDoneForTest() {
        try {
            return ResponseEntity.ok(subjectService.getAllDoneForTest());
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body("Ошибка " + e.getMessage());
        }
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(subjectService.getOne(id));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/subject/setdelete/{id}")
    public ResponseEntity<?> setDeleteStatus(@PathVariable Long id) {
        try {
            subjectService.setDeleteStatusNew(id);
            return ResponseEntity.ok("Ok");
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/subject")
    public ResponseEntity<?> create(@Valid @RequestBody SubjectAddDto subjectAddDto) {
        try {
            return ResponseEntity.ok(subjectService.create(subjectAddDto));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/subjects")
    public ResponseEntity<?> updateAll(@Valid @RequestBody List<SubjectAddDto> subjectAddDtos) {
        try {
            return ResponseEntity.ok(subjectService.updateAll(subjectAddDtos));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PutMapping("/subject")
    public ResponseEntity<?> updateOne(@Valid @RequestBody SubjectAddDto subjectAddDto) {
        try {
            return ResponseEntity.ok(subjectService.updateOne(subjectAddDto));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
