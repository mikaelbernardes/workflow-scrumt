package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.application.service.UserService;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.presentation.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
                createdUser.getUpdatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO
    ){
        User updateUser = userService.updateUser(id, userDTO);
        UserResponse response = new UserResponse(
                updateUser.getId(),
                updateUser.getName(),
                updateUser.getEmail(),
                updateUser.getCreatedAt(),
                updateUser.getUpdatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> patchUser(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        User patchedUser = userService.patchUser(id, updates);
        UserResponse response = new UserResponse(
                patchedUser.getId(),
                patchedUser.getName(),
                patchedUser.getEmail(),
                patchedUser.getCreatedAt(),
                patchedUser.getUpdatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
        User findUser = userService.findUserById(id);
        UserResponse response = new UserResponse(
                findUser.getId(),
                findUser.getName(),
                findUser.getEmail(),
                findUser.getCreatedAt(),
                findUser.getUpdatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
