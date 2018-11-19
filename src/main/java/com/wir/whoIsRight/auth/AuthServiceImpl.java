package com.wir.whoIsRight.auth;

import com.wir.whoIsRight.user.UserEntity;
import com.wir.whoIsRight.exception.BadRequestException;
import com.wir.whoIsRight.user.UserRepository;
import com.wir.whoIsRight.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;

    public String authenticateUser(Login login) {

        Authentication authentication =
              authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    public URI registerUser(SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating userEntity's account
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signUp.getName());
        userEntity.setEmail(signUp.getEmail());
        userEntity.setPassword(signUp.getPassword());
        userEntity.setProvider(AuthProviderEnum.local);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        UserEntity result = userRepository.save(userEntity);

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/userEntity/me").buildAndExpand(result.getId()).toUri();
    }

}
