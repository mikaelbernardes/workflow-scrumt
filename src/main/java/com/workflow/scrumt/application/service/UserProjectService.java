package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.application.mapper.UserProjectMapper;
import com.workflow.scrumt.application.validation.UserProjectValidation;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.domain.repository.UserProjectRepository;
import com.workflow.scrumt.domain.useCase.userProject.PatchUserProjectRoleUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectService implements PatchUserProjectRoleUseCase {

    @Autowired
    private UserProjectRepository userProjectRepository;
    @Autowired
    private UserProjectMapper userProjectMapper;
    @Autowired
    private UserProjectValidation userProjectValidation;

    @Override
    public UserProject patchUserProjectRole(Long id, UserProjectDTO userProjectDTO) {
        userProjectValidation.validatePatchRole(id, userProjectDTO);
        UserProject existingUserProject = userProjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserProject not found"));

        existingUserProject.setRole(userProjectDTO.role());

        return userProjectRepository.save(existingUserProject);
    }
}
