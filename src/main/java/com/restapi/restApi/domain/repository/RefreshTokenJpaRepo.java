package com.restapi.restApi.domain.repository;

import com.restapi.restApi.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenJpaRepo extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByKey(Long key);

//    Long deleteByKey(Long key);
}
