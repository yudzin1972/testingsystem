package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.RecordAddException;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.OptionAddDto;
import by.yudzin.testingsystem.service.OptionService;
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
public class OptionRestController {
    private final OptionService optionService;

    @GetMapping("/options/{questiontId}")
    public ResponseEntity<?> getByQuestionIdDone(@PathVariable Long questiontId) {
        try {
            return ResponseEntity.ok(optionService.getOptionsByQuestionId(questiontId));
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/options")
    public ResponseEntity<?> updateAll(@Valid @RequestBody List<OptionAddDto> optionAddDtos) {
        try {
            return ResponseEntity.ok(optionService.updateAll(optionAddDtos));
        } catch (RecordAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_TEACHER') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/option/setdelete/{id}")
    public ResponseEntity<?> setDeleteStatus(@PathVariable Long id) {
        try {
            optionService.setDeleteStatusNew(id);
            return ResponseEntity.ok("Ok");
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(406).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/options/{questiontId}")
    public ResponseEntity<?> getByQuestionIdDoneOrderRand(@PathVariable Long questiontId) {
        try {
            return ResponseEntity.ok(optionService.getOptionsByQuestionIdOrderRand(questiontId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body("Ошибка " + e.getMessage());
        }
    }
}
