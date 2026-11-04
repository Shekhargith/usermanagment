package com.usermanagement.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.user.dto.UpdateUserRequest;
import com.usermanagement.user.dto.UserResponse;
import com.usermanagement.entities.shahiWeb.RoleEntity;
import com.usermanagement.entities.shahiWeb.UserEntity;
import com.usermanagement.entities.shahiWeb.UserRoleEntity;
import com.usermanagement.repository.shahiWeb.RoleRepository;
import com.usermanagement.repository.shahiWeb.UserRepository;
import com.usermanagement.repository.shahiWeb.UserRoleRepository;
import com.usermanagement.user.dto.CreateUserRequest;


@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
   // private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Saves a new user in UM_USERS. The password is stored as a BCrypt hash. */
    @Transactional
    public UserResponse saveUser(CreateUserRequest request) {
        String username = request.username().trim();
        String email = request.email().trim().toLowerCase();

        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new IllegalArgumentException("Username is already registered");
        }
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email is already registered");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
       // user.setPassword(passwordEncoder.encode(request.password()));
        user.setFullName(request.fullName().trim());
        user.setMobileNo(request.mobileNo() == null || request.mobileNo().isBlank()
                ? null : request.mobileNo().trim());
        user.setStatus("ACTIVE");
        user.setEmailVerified(0);
        // Password BCrypt encryption
        user.setPassword(
            passwordEncoder.encode(request.password())
        );
        UserEntity savedUser = userRepository.save(user);
        
        // Validate roles
        if (request.roles() == null || request.roles().isEmpty()) {
            throw new RuntimeException("Please select at least one role");
        }

        // Save multiple roles
        for (String roleName : request.roles()) {

            RoleEntity role = roleRepository.findByRoleNameIgnoreCase(roleName)
                    .orElseThrow(() ->
                        new RuntimeException("Role not found: " + roleName)
                    );

            UserRoleEntity mapping = new UserRoleEntity();

            mapping.setUser(savedUser);
            mapping.setRole(role);

            userRoleRepository.save(mapping);
        }

       
        
        return toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }
    public UserResponse getUserById(Long id) throws Exception { return toResponse(getEntity(id)); }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) throws Exception {
        UserEntity user = getEntity(id);
        userRepository.findByEmailIgnoreCase(request.email()).ifPresent(existing -> {
            if (!existing.getUserId().equals(id)) throw new IllegalArgumentException("Email is already in use");
        });
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setMobileNo(request.mobileNo());
        user.setUpdatedDate(LocalDateTime.now());
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateStatus(Long id, String status) throws Exception {
        if (!List.of("ACTIVE", "INACTIVE", "LOCKED").contains(status))
            throw new IllegalArgumentException("Status must be ACTIVE, INACTIVE or LOCKED");
        UserEntity user = getEntity(id);
        user.setStatus(status);
        user.setUpdatedDate(LocalDateTime.now());
        return toResponse(userRepository.save(user));
    }

    private UserEntity getEntity(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found: " + id));
    }
    private UserResponse toResponse(UserEntity u) {
        return new UserResponse(u.getUserId(), u.getUsername(), u.getEmail(), u.getMobileNo(), u.getFullName(),
            u.getStatus(), u.getEmailVerified(), u.getCreatedDate(), u.getUpdatedDate());
    }
}
