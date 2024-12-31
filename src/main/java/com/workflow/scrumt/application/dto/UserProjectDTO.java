package com.workflow.scrumt.application.dto;

import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.enums.UserRole;

public record UserProjectDTO(
        Long id,
        Long userId,
        Long projectId,
        String userName,
        String projectName,
        UserRole role
) {
}
