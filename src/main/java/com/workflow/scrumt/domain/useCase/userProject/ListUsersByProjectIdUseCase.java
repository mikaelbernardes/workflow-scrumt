package com.workflow.scrumt.domain.useCase.userProject;

import com.workflow.scrumt.domain.entity.User;

import java.util.List;

public interface ListUsersByProjectIdUseCase {
    List<User> getUsersByProjectId(Long projectId);
}
