package com.usermanagement.repository.shahiWeb;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.entities.shahiWeb.RefreshTokenEntity;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByTokenHash(String tokenHash);
    List<RefreshTokenEntity> findByUser_UserId(Long userId);
    List<RefreshTokenEntity> findByUser_UserIdAndIsRevoked(Long userId, Integer isRevoked);
    void deleteByUser_UserId(Long userId);
}
