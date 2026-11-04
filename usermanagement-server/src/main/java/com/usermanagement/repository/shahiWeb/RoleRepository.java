package com.usermanagement.repository.shahiWeb;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.usermanagement.entities.shahiWeb.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleNameIgnoreCase(String roleName);
    boolean existsByRoleNameIgnoreCase(String roleName);
}
