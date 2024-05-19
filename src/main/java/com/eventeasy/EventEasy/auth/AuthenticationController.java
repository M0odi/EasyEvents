package com.eventeasy.EventEasy.auth;

import com.eventeasy.EventEasy.auth.requsest.AuthenticationRequest;
import com.eventeasy.EventEasy.auth.requsest.AuthenticationService;
import com.eventeasy.EventEasy.auth.requsest.RegisterRequest;
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
                                      @RequestParam(name = "password") String password,@RequestParam
                                                  (
            name = "email"
    ) String email) {

        return registerCommandHandler.execute(RegisterRequest.builder().password(password).login(login).email(email).build());

    }

    @PostMapping("/authenticate")
    public RedirectView authenticate(
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email") String email) {
        String token = authenticationService.authenticate(AuthenticationRequest.builder().password(password).email(email).build()).getToken();

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/secured/create-event-template");
        return redirectView;
    }
}
