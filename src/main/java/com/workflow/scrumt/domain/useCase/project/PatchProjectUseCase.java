package com.workflow.scrumt.domain.useCase.project;

import com.workflow.scrumt.domain.entity.Project;

import java.util.Map;

public interface PatchProjectUseCase {
    Project patchProject(Long id, Map<String, Object> updates);
}
