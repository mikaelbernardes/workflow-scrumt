package com.workflow.scrumt.domain.entity;

import com.workflow.scrumt.domain.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be null or empty")
    @Size(min = 4, max = 100, message = "The username cannot be shorter than 4 characters nor longer than 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "E-mail cannot be null or empty")
    @Email(message = "Invalid email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 100, message = "The user password cannot be shorter than 8 characters and cannot exceed 100 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one number, and one special character."
    )
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Min(value = 0)
    @Column(name = "failed_login_attempts", nullable = false)
    private byte failedLoginAttempts;

    @Column(name = "account_locked", nullable = false)
    private boolean accountLocked;

    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    @Column(name = "two_factor_enabled", nullable = false)
    private boolean twoFactorEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
}
