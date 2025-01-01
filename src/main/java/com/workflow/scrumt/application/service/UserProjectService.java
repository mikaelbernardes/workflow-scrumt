package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.application.mapper.UserProjectMapper;
import com.workflow.scrumt.application.validation.UserProjectValidation;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.domain.enums.UserRole;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import com.workflow.scrumt.domain.repository.UserProjectRepository;
import com.workflow.scrumt.domain.useCase.userProject.PatchUserProjectRoleUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new CustomException("UserProject not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));

        if (existingUserProject.getRole() == UserRole.OWNER && userProjectDTO.role() != UserRole.OWNER) {
            Long projectId = existingUserProject.getProject().getId();
            long ownerCount = userProjectRepository.countByProjectIdAndRole(projectId, UserRole.OWNER);

            if (ownerCount == 1) {
                throw new CustomException(
                        "Cannot change the role of this user because they are the only OWNER of the project.", ExceptionLevel.WARNING, HttpStatus.BAD_REQUEST
                );
            }
        }

        existingUserProject.setRole(userProjectDTO.role());

        return userProjectRepository.save(existingUserProject);
    }
}
