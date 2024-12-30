package com.workflow.scrumt.domain.useCase.project;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.domain.entity.Project;

public interface CreateProjectUseCase {
    Project createProject(ProjectDTO project);
}
