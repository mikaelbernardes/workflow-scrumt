package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.application.service.UserProjectService;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.domain.enums.UserRole;
import com.workflow.scrumt.presentation.response.ProjectResponse;
import com.workflow.scrumt.presentation.response.UserListWithRoleResponse;
import com.workflow.scrumt.presentation.response.UserProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userProject")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    @PatchMapping("/{id}")
    public ResponseEntity<UserProjectResponse> patchUserProjectRole(@PathVariable Long id, @RequestBody UserProjectDTO userProjectDTO) {
        UserProject patchUserProject = userProjectService.patchUserProjectRole(id, userProjectDTO);
        UserProjectResponse response = new UserProjectResponse(
                patchUserProject.getId(),
                patchUserProject.getUser().getId(),
                patchUserProject.getProject().getId(),
                patchUserProject.getRole()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usersByProject/{projectId}")
    public ResponseEntity<List<UserListWithRoleResponse>> getUsersByProject(@PathVariable Long projectId) {
        List<User> users = userProjectService.getUsersByProjectId(projectId);
        List<UserListWithRoleResponse> userResponses = users.stream()
                .map(user -> {
                    UserRole role = user.getUserProjects().stream()
                            .filter(up -> up.getProject().getId().equals(projectId))
                            .findFirst()
                            .map(UserProject::getRole)
                            .orElse(UserRole.UNDEFINED);

                    return new UserListWithRoleResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            role
                    );
                })
                .toList();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/projectsByUser/{userId}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByUser(@PathVariable Long userId) {
        List<Project> projects = userProjectService.getProjectsByUserId(userId);

        List<ProjectResponse> projectResponse = projects.stream()
                .map(project -> new ProjectResponse(
                        project.getId(),
                        project.getName(),
                        project.getDescription(),
                        project.getCreatedAt()
                )).toList();
        return ResponseEntity.ok(projectResponse);
    }

}
