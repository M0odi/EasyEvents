package com.eventeasy.EventEasy.auth;

import com.eventeasy.EventEasy.auth.requsest.AuthenticationRequest;
import com.eventeasy.EventEasy.auth.requsest.AuthenticationService;
import com.eventeasy.EventEasy.auth.requsest.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final RegisterCommandHandler registerCommandHandler;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest user) {

        return registerCommandHandler.execute(user);

    }

    @PostMapping("/login")
    public ResponseEntity<?> SignUp(@RequestBody AuthenticationRequest user) {
        try {
            String token = authenticationService.authenticate(user).getToken();
            return ResponseEntity.ok(token);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }

}
