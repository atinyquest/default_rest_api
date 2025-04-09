package com.example.kdh.common.service;

import com.example.kdh.common.model.CustomUserDetails;
import com.example.kdh.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));
        return new CustomUserDetails(user.getId(), user.getName(), user.getPassword());
    }
}
