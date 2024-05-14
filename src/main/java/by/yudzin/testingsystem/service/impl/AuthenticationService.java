package by.yudzin.testingsystem.service.impl;

import by.yudzin.testingsystem.config.Constants;
import by.yudzin.testingsystem.enums.Role;
import by.yudzin.testingsystem.exception.DublicatAddException;
import by.yudzin.testingsystem.exception.PasswordDataMismatch;
import by.yudzin.testingsystem.exception.RecordNotFountException;
import by.yudzin.testingsystem.model.dto.ChangePasswordRequest;
import by.yudzin.testingsystem.model.dto.JwtAuthenticationResponse;
import by.yudzin.testingsystem.model.dto.SignInRequest;
import by.yudzin.testingsystem.model.dto.SignUpRequest;
import by.yudzin.testingsystem.model.entity.UserEntity;
import by.yudzin.testingsystem.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final Constants constants;
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws DublicatAddException {

        var user = UserEntity.builder()
                .username(request.getUsername())
                .fio(request.getFio())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userServiceImpl.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {

        UserDetails user = userServiceImpl
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        authenticationManager.authenticate(upat);

        return new JwtAuthenticationResponse(jwt);
    }
    @Transactional
    public JwtAuthenticationResponse changePassword(ChangePasswordRequest changePasswordRequest) throws RecordNotFountException, PasswordDataMismatch {
        UserEntity userEntity = repository.findById(changePasswordRequest.getId()).orElseThrow(() -> new UsernameNotFoundException(constants.ERROR_RECORD_NOT_FOUND_CONSTANT));
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), userEntity.getPassword())) {
            throw new PasswordDataMismatch(constants.ERROR_OLD_PASSWORD_CONSTANT);
        }
        userEntity.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        repository.saveAndFlush(userEntity);
        SignInRequest signInRequest=new SignInRequest();
        signInRequest.setPassword(changePasswordRequest.getPassword());
        signInRequest.setUsername(userEntity.getUsername());
        return signIn(signInRequest);
    }
}
