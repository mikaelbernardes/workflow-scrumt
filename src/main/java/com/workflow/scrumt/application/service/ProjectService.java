package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.mapper.ProjectMapper;
import com.workflow.scrumt.application.validation.ProjectValidation;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.repository.ProjectRepository;
import com.workflow.scrumt.domain.useCase.project.CreateProjectUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectService implements CreateProjectUseCase {

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
}
