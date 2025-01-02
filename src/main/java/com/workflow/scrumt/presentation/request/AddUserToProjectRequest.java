package com.workflow.scrumt.presentation.request;

import com.workflow.scrumt.domain.enums.UserRole;

public record AddUserToProjectRequest(
        Long projectId,
        Long userId,
        UserRole role
) {
}
