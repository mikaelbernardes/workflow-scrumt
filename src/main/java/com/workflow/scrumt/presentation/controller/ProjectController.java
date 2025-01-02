package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.application.service.ProjectService;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.presentation.request.AddUserToProjectRequest;
import com.workflow.scrumt.presentation.response.ProjectResponse;
import com.workflow.scrumt.presentation.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectDTO projectDTO
    ){
        Project updateProject = projectService.updateProject(id, projectDTO);
        ProjectResponse response = new ProjectResponse(
                updateProject.getId(),
                updateProject.getName(),
                updateProject.getDescription(),
                updateProject.getCreatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> patchProject(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        Project patchedUser = projectService.patchProject(id, updates);
        ProjectResponse response = new ProjectResponse(
                patchedUser.getId(),
                patchedUser.getName(),
                patchedUser.getDescription(),
                patchedUser.getCreatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addUserToProject")
    public ResponseEntity<Void> addUserToProject(@RequestBody AddUserToProjectRequest request) {
        projectService.addUserToProject(request.projectId(), request.userId(), request.role());
        return ResponseEntity.noContent().build();
    }
}
