package com.usermanagement.repository.shahiWeb;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.entities.shahiWeb.LoginHistoryEntity;

public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity, Long> {
    List<LoginHistoryEntity> findByUser_UserIdOrderByLoginDateDesc(Long userId);
    List<LoginHistoryEntity> findByUsernameIgnoreCaseOrderByLoginDateDesc(String username);
    List<LoginHistoryEntity> findByLoginStatusOrderByLoginDateDesc(String loginStatus);
}
