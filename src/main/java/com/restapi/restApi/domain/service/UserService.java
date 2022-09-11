package com.restapi.restApi.domain.service;

import com.restapi.restApi.domain.entity.User;
import com.restapi.restApi.domain.repository.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserJpaRepo userJpaRepository;

    /*@Transactional
    public Long save()*/

    public List<User> findAllUser() {
        return userJpaRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }
}
