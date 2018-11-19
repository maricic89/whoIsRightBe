package com.wir.whoIsRight.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class Login {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;


}
