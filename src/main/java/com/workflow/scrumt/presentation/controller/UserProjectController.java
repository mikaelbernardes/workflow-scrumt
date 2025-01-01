package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.application.service.UserProjectService;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.presentation.response.UserProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
