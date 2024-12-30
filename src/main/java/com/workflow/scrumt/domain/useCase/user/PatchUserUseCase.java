package com.workflow.scrumt.domain.useCase.user;

import com.workflow.scrumt.domain.entity.User;

import java.util.Map;

public interface PatchUserUseCase {
    User patchUser(Long id, Map<String, Object> updates);
}
