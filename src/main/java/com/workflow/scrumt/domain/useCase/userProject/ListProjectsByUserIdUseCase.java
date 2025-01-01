package com.workflow.scrumt.domain.useCase.userProject;

import com.workflow.scrumt.domain.entity.Project;

import java.util.List;

public interface ListProjectsByUserIdUseCase {
    List<Project> getProjectsByUserId(Long userId);
}
