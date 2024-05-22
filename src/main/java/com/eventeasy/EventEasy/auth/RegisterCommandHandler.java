package com.eventeasy.EventEasy.auth;

import com.eventeasy.EventEasy.Command;
import com.eventeasy.EventEasy.auth.requsest.AuthenticationService;
import com.eventeasy.EventEasy.auth.requsest.RegisterRequest;
import com.eventeasy.EventEasy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCommandHandler implements Command<RegisterRequest, Void> {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> execute(RegisterRequest request) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different email");
        }
        return ResponseEntity.ok(authenticationService.register(request).getToken());
    }
}
