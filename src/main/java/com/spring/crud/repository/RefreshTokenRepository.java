package com.spring.crud.repository;

import com.spring.crud.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByContent(String content);

    Optional<RefreshToken> findByTbUserId(String tbUserId);

    void deleteAllByTbUserId(String tbUserId);
}
