package com.spring.crud.repository;

import com.spring.crud.domain.TbUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserRepository extends JpaRepository<TbUser, String> {
    TbUser findByUsername(String username);

    // 최초 조회시 JOIN을 사용하기 위해 쓰는 어노테이션
    @EntityGraph(attributePaths = {"tbUserRoleType.roleType"})
    Optional<TbUser> findEntityGraphRoleTypeById(String id);
}
