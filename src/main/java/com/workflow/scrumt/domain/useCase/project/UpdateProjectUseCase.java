package com.workflow.scrumt.domain.useCase.project;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.domain.entity.Project;

public interface UpdateProjectUseCase {
    Project updateProject(Long id, ProjectDTO project);
}
