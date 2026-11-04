package com.usermanagement.user.dto;
import java.time.LocalDateTime;
public record UserRoleResponse(Long userRoleId, Long userId, String username, Long roleId, String roleName,
        LocalDateTime assignedDate, Long assignedBy) {}
