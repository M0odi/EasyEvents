package com.eventeasy.EventEasy.auth.requsest;

import lombok.*;

@Getter
@Data
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String email;
    private String password;

}
