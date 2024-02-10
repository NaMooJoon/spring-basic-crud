package com.spring.crud.repository;

import com.spring.crud.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByContent(String content);

    Optional<RefreshToken> findByUsername(String tbUserId);

    @Transactional
    void deleteAllByUsername(String tbUserId);
}
