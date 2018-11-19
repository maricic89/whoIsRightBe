package com.wir.whoIsRight.user;

import com.wir.whoIsRight.exception.ResourceNotFoundException;
import com.wir.whoIsRight.security.CurrentUser;
import com.wir.whoIsRight.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserEndpoint {

    private UserRepository userRepository;

    @GetMapping("me")
    @PreAuthorize("hasRole('USER')")
    public UserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository
                     .findById(userPrincipal.getId())
                     .orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userPrincipal.getId()));
    }
}
