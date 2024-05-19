package com.eventeasy.EventEasy.auth.requsest;

import lombok.*;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    private String email;
    private String password;

}
