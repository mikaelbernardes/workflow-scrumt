package com.workflow.scrumt.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ProjectDTO {

    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 2, max = 150, message = "The project name cannot be shorter than 2 characters and cannot exceed 150 characters")
    private String name;

    private String description;

    private LocalDateTime createdAt;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
