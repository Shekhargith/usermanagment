package com.usermanagement.user.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.usermanagement.user.dto.AssignRoleRequest;
import com.usermanagement.user.dto.UserRoleResponse;
import com.usermanagement.user.service.UserRoleService;
@RestController
@RequestMapping("/api/users/{userId}/roles")
public class UserRoleController {
    private final UserRoleService service;
    public UserRoleController(UserRoleService service) { this.service = service; }
    @GetMapping public ResponseEntity<List<UserRoleResponse>> getRoles(@PathVariable Long userId) throws Exception { return ResponseEntity.ok(service.getRolesForUser(userId)); }
    //@PostMapping public ResponseEntity<UserRoleResponse> assign(@PathVariable Long userId, @Validated @RequestBody AssignRoleRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.assignRole(userId, request.roleId(), request.assignedBy())); }
    //@DeleteMapping("/{roleId}") public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long roleId) { service.removeRole(userId, roleId); return ResponseEntity.noContent().build(); }
}
