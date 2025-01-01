package com.workflow.scrumt.domain.useCase.userProject;

import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.domain.entity.UserProject;

public interface PatchUserProjectRoleUseCase {
    UserProject patchUserProjectRole(Long id, UserProjectDTO userProjectDTO);
}
