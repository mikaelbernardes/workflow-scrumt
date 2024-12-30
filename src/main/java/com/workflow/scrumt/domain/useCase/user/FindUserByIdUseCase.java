package com.workflow.scrumt.domain.useCase.user;

import com.workflow.scrumt.domain.entity.User;

public interface FindUserByIdUseCase {
    User findUserById (Long id);
}
