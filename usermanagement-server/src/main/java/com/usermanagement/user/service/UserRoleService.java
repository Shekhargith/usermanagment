package com.usermanagement.user.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.user.dto.UserRoleResponse;
import com.usermanagement.entities.shahiWeb.UserEntity;
import com.usermanagement.entities.shahiWeb.UserRoleEntity;
import com.usermanagement.repository.shahiWeb.RoleRepository;
import com.usermanagement.repository.shahiWeb.UserRepository;
import com.usermanagement.repository.shahiWeb.UserRoleRepository;


@Service
@Transactional(readOnly = true)
public class UserRoleService {
    private final UserRoleRepository userRoleRepository; private final UserRepository userRepository; private final RoleRepository roleRepository;
    public UserRoleService(UserRoleRepository ur, UserRepository u, RoleRepository r) { this.userRoleRepository=ur; this.userRepository=u; this.roleRepository=r; }
    public List<UserRoleResponse> getRolesForUser(Long userId) throws Exception {
        if (!userRepository.existsById(userId)) throw new Exception("User not found: " + userId);
        return userRoleRepository.findByUser_UserId(userId).stream().map(this::toResponse).toList();
    }
    @Transactional
    public UserRoleResponse assignRole(Long userId, Long roleId, Long assignedBy) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found: " + userId));
        var role = roleRepository.findById(roleId).orElseThrow(() -> new Exception("Role not found: " + roleId));
        if (userRoleRepository.existsByUser_UserIdAndRole_RoleId(userId, roleId)) throw new IllegalArgumentException("Role is already assigned to this user");
        UserRoleEntity link = new UserRoleEntity(); link.setUser(user); link.setRole(role); link.setAssignedBy(assignedBy);
        return toResponse(userRoleRepository.save(link));
    }
    @Transactional
    public void removeRole(Long userId, Long roleId) throws Exception {
        UserRoleEntity link = userRoleRepository.findByUser_UserIdAndRole_RoleId(userId, roleId)
            .orElseThrow(() -> new Exception("User role assignment not found"));
        userRoleRepository.delete(link);
    }
    private UserRoleResponse toResponse(UserRoleEntity x) { return new UserRoleResponse(x.getUserRoleId(), x.getUser().getUserId(), x.getUser().getUsername(), x.getRole().getRoleId(), x.getRole().getRoleName(), x.getAssignedDate(), x.getAssignedBy()); }
}
