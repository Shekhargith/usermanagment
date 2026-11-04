package com.usermanagement.user.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.usermanagement.user.dto.UpdateUserRequest;
import com.usermanagement.user.dto.CreateUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.usermanagement.user.dto.UserResponse;
import com.usermanagement.user.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveUser(
            @RequestBody CreateUserRequest request) {
    	

        UserResponse response = userService.saveUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}