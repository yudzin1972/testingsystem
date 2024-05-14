package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.QuestionAddDto;
import by.yudzin.testingsystem.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tsystem")
@RequiredArgsConstructor
@Slf4j
public class QuestionRestController {
    private final QuestionService questionService;

    @GetMapping("/questions/{subjectId}")
    public ResponseEntity<?> getBySubjectIdDone(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(questionService.getQuestionsBySubjectId(subjectId));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/questions")
    public ResponseEntity<?> updateAll(@Valid @RequestBody List<QuestionAddDto> questionAddDtos) {
        try {
            return ResponseEntity.ok(questionService.updateAll(questionAddDtos));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/question/setdelete/{id}")
    public ResponseEntity<?> setDeleteStatus(@PathVariable Long id) {
        try {
            questionService.setDeleteStatusNew(id);
            return ResponseEntity.ok("Ok");
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
