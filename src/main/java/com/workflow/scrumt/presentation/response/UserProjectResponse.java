package com.workflow.scrumt.presentation.response;

import com.workflow.scrumt.domain.enums.UserRole;

public record UserProjectResponse(Long id, Long userId, Long projectId, UserRole role) {
}
