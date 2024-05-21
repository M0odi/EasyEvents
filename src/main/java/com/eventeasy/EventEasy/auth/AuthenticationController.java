package com.eventeasy.EventEasy.auth;

import com.eventeasy.EventEasy.auth.requsest.AuthenticationRequest;
import com.eventeasy.EventEasy.auth.requsest.AuthenticationService;
import com.eventeasy.EventEasy.auth.requsest.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final RegisterCommandHandler registerCommandHandler;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam(name = "login") String login,
                                      @RequestParam(name = "password") String password, @RequestParam
                                              (
                                                      name = "email"
                                              ) String email) {

        return ResponseEntity.ok(registerCommandHandler.execute(RegisterRequest.builder().password(password).login(login).email(email).build()));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email") String email) {
        try {
            String token = authenticationService.authenticate(
                    AuthenticationRequest.builder().password(password).email(email).build()).getToken();

            System.out.println("Retrieved token: " + token);

/*
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/secured/create-event-template");
            return redirectView;*/
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
