package com.workflow.scrumt.domain.useCase.user;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.domain.entity.User;

public interface UpdateUserUseCase {
    User updateUser(Long id, UserDTO user);
}
