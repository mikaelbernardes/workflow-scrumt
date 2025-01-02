package com.workflow.scrumt.domain.useCase.project;

import com.workflow.scrumt.domain.enums.UserRole;

public interface AddUserToProjectUseCase {
    boolean addUserToProject(Long projectId, Long userId, UserRole role);
}
