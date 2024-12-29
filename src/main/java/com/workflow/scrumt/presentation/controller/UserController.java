package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.application.service.UserService;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.presentation.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        UserResponse response = new UserResponse(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail(),
                createdUser.getCreatedAt(),
                createdUser.getUpdatedAt(),
                createdUser.getFailedLoginAttempts(),
                createdUser.isAccountLocked(),
                createdUser.getLockTime(),
                createdUser.isTwoFactorEnabled()
        );
        return ResponseEntity.ok(response);
    }
}
