package com.usermanagement.user.dto;
import java.time.LocalDateTime;
public record UserResponse(Long userId, String username, String email, String mobileNo, String fullName,
        String status, Integer emailVerified, LocalDateTime createdDate, LocalDateTime updatedDate) {}
