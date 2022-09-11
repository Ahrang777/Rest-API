package com.restapi.restApi.domain.service.security;

import com.restapi.restApi.advice.exception.CUserNotFoundException;
import com.restapi.restApi.domain.repository.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepo userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        return userJpaRepository.findById(Long.parseLong(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}
