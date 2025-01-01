package com.workflow.scrumt.presentation.response;

import com.workflow.scrumt.domain.enums.UserRole;

public record UserListWithRoleResponse(
        Long id,
        String name,
        String email,
        UserRole role
) {
}
