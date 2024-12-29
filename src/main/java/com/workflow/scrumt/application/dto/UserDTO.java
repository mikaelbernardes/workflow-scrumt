package com.workflow.scrumt.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 4, max = 100, message = "The name cannot be shorter than 4 characters nor longer than 100 characters")
    private String name;

    @NotBlank(message = "E-mail cannot be null or empty")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 100, message = "The user password cannot be shorter than 8 characters and cannot exceed 100 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserDTO() {
    }

    public UserDTO(
            Long id,
            String name,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
