package com.usermanagement.repository.shahiWeb;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.entities.shahiWeb.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findByUser_UserId(Long userId);
    Optional<UserRoleEntity> findByUser_UserIdAndRole_RoleId(Long userId, Long roleId);
    boolean existsByUser_UserIdAndRole_RoleId(Long userId, Long roleId);
}
