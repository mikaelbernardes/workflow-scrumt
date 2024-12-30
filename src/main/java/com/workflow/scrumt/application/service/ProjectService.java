package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.mapper.ProjectMapper;
import com.workflow.scrumt.application.validation.ProjectValidation;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import com.workflow.scrumt.domain.repository.ProjectRepository;
import com.workflow.scrumt.domain.useCase.project.CreateProjectUseCase;
import com.workflow.scrumt.domain.useCase.project.DeleteProjectUseCase;
import com.workflow.scrumt.domain.useCase.project.PatchProjectUseCase;
import com.workflow.scrumt.domain.useCase.project.UpdateProjectUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProjectService implements
        CreateProjectUseCase,
        UpdateProjectUseCase,
        PatchProjectUseCase,
        DeleteProjectUseCase
{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectValidation projectValidation;

    @Override
    public Project createProject(ProjectDTO project) {
        projectValidation.validateCreate(project);
        project.setCreatedAt(LocalDateTime.now());
        Project newProject = projectMapper.toEntity(project);
        return projectRepository.save(newProject);
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
}
