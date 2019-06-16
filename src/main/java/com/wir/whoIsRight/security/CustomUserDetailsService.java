package com.wir.whoIsRight.security;

import com.wir.whoIsRight.exception.ResourceNotFoundException;
import com.wir.whoIsRight.user.UserEntity;
import com.wir.whoIsRight.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                                      .findByEmail(email)
                                      .orElseThrow(() -> new UsernameNotFoundException("UserEntity not found with email : " + email));

        return UserPrincipal.create(userEntity);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", id));

        return UserPrincipal.create(userEntity);
    }
}