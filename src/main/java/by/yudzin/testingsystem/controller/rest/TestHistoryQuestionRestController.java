package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.TestsHistoryQuestionDoneDto;
import by.yudzin.testingsystem.service.TestHistoryQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tsystem")
@RequiredArgsConstructor
@Slf4j
public class TestHistoryQuestionRestController {
    private final TestHistoryQuestionService testHistoryQuestionService;

    @PostMapping("/testhistoryquestion")
    public ResponseEntity<?> setDoneHistoryQuestion(@RequestBody TestsHistoryQuestionDoneDto testsHistoryQuestionDoneDto) {
        try {
            testHistoryQuestionService.setDoneStatus(testsHistoryQuestionDoneDto);
            return ResponseEntity.ok("Ok");
        } catch (RecordNotFountException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
