package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.service.ProjectService;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.presentation.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectDTO projectDTO) {
        Project createdProject = projectService.createProject(projectDTO);
        ProjectResponse response = new ProjectResponse(
                createdProject.getId(),
                createdProject.getName(),
                createdProject.getDescription(),
                createdProject.getCreatedAt()
        );
        return ResponseEntity.ok(response);
    }

}
