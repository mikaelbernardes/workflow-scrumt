package com.workflow.scrumt.application.dto;

import com.workflow.scrumt.domain.enums.UserRole;

public record UserProjectDTO(
        UserRole role
) {
}
