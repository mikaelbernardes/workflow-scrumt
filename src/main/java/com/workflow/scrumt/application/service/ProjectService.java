package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.mapper.ProjectMapper;
import com.workflow.scrumt.application.validation.ProjectValidation;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.domain.enums.UserRole;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import com.workflow.scrumt.domain.repository.ProjectRepository;
import com.workflow.scrumt.domain.repository.UserProjectRepository;
import com.workflow.scrumt.domain.useCase.project.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProjectService implements
        CreateProjectUseCase,
        UpdateProjectUseCase,
        PatchProjectUseCase,
        DeleteProjectUseCase,
        AddUserToProjectUseCase
{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectValidation projectValidation;
    @Autowired
    private UserProjectRepository userProjectRepository;

    @Override
    public Project createProject(ProjectDTO project) {
        projectValidation.validateCreate(project);

        String userId = getAuthenticatedUserId();
        if (userId == null || userId.isBlank()) {
            throw new CustomException("User id not found", ExceptionLevel.CRITICAL, HttpStatus.UNAUTHORIZED);
        }

        project.setCreatedAt(LocalDateTime.now());

        Project newProject = projectMapper.toEntity(project);
        Project savedProject = projectRepository.save(newProject);

        UserProject userProject = new UserProject();
        userProject.setUser(new User(Long.parseLong(userId)));
        userProject.setProject(savedProject);
        userProject.setRole(UserRole.OWNER);
        userProjectRepository.save(userProject);

        return savedProject;
    }

    @Override
    public Project updateProject(Long id, ProjectDTO project) {
        projectValidation.validateUpdate(id, project);
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Project not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));

        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());

        return projectRepository.save(existingProject);
    }

    @Override
    public Project patchProject(Long id, Map<String, Object> updates) {
        projectValidation.validatePatch(id, updates);
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Project not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingProject.setName((String) value);
                    break;
                case "description":
                    existingProject.setDescription((String) value);
                    break;
                default:
                    throw new CustomException("Invalid field: " + key, ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
            }
        });

        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        projectValidation.validateDelete(id);
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Project not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));
        projectRepository.deleteById(existingProject.getId());
    }

    private String getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getId().toString();
        }
        return null;
    }

    private boolean isUserOwnerOfProject(Long projectId, Long userId) {
        UserProject userProject = userProjectRepository.findByProjectIdAndUserId(projectId, userId);
        return userProject != null && userProject.getRole().equals(UserRole.OWNER);
    }

    @Override
    public boolean addUserToProject(Long projectId, Long userId, UserRole role) {

        if (!UserRole.isValid(role)) {
            throw new CustomException("Invalid role specified", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }

        String currentUserId = getAuthenticatedUserId();

        if (currentUserId == null || currentUserId.isBlank()) {
            throw new CustomException("User id not found", ExceptionLevel.CRITICAL, HttpStatus.UNAUTHORIZED);
        }

        if (!isUserOwnerOfProject(projectId, Long.parseLong(currentUserId))) {
            throw new CustomException("Only the owner user can add new members", ExceptionLevel.ERROR, HttpStatus.UNAUTHORIZED);
        }

        if (userProjectRepository.existsByProjectIdAndUserId(projectId, userId)) {
            throw new CustomException("This user is already a member of this project", ExceptionLevel.INFO, HttpStatus.BAD_REQUEST);
        }

        UserProject userProject = new UserProject();
        userProject.setProject(new Project(projectId));
        userProject.setUser(new User(userId));
        userProject.setRole(role);

        userProjectRepository.save(userProject);
        return true;
    }
}
