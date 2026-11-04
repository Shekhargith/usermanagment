package com.usermanagement.repository.shahiWeb;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.entities.shahiWeb.PasswordResetTokenEntity;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
    Optional<PasswordResetTokenEntity> findByTokenHash(String tokenHash);
    List<PasswordResetTokenEntity> findByUser_UserId(Long userId);
    List<PasswordResetTokenEntity> findByUser_UserIdAndIsUsed(Long userId, Integer isUsed);
    void deleteByExpiryDateBefore(LocalDateTime dateTime);
}
