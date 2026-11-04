package com.usermanagement.user.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record UpdateUserRequest(
    @NotBlank @Size(max=150) String fullName,
    @NotBlank @Email @Size(max=150) String email,
    @Size(max=15) String mobileNo
) {}
