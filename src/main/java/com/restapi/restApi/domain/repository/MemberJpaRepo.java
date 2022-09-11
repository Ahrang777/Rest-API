package com.restapi.restApi.domain.repository;

import com.restapi.restApi.domain.entity.Member;
import com.restapi.restApi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberJpaRepo extends JpaRepository<Member, Long> {
    List<Member> findMemberByUser(User user);

}
