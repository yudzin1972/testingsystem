package by.yudzin.testingsystem.controller.rest;

import by.yudzin.testingsystem.exception.DublicatAddException;
import by.yudzin.testingsystem.model.dto.SignInRequest;
import by.yudzin.testingsystem.model.dto.SignUpRequest;
import by.yudzin.testingsystem.service.impl.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/tsystem"})
@Slf4j
public class AuthRestController {


    private final AuthenticationService authenticationService;


    @PostMapping("/auth/user")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signUp(request));
        } catch (DublicatAddException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signIn(request));
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
