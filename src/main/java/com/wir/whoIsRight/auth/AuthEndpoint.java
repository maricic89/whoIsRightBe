package com.wir.whoIsRight.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthEndpoint {

    private AuthService authenticate;

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login login) {
        String token = authenticate.authenticateUser(login);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUp signUp) {

        URI location = authenticate.registerUser(signUp);

        return ResponseEntity.created(location).body(new ApiResponse(true, "UserEntity registered successfully@"));
    }

}
