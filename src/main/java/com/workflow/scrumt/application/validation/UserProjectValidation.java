package com.workflow.scrumt.application.validation;

import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProjectValidation extends EntityValidation<UserProject> {
    @Override
    public void validateCreate(UserProject entity) {

    }

    @Override
    public void validateUpdate(Long id, UserProject entity) {

    }

    @Override
    public void validatePatch(Long id, Map<String, Object> updates) {
    }

    @Override
    public void validateDelete(Long id) {

    }

    @Override
    public void validateRead(Long id) {

    }

    public void validatePatchRole(Long id, UserProjectDTO userProjectDTO) {
        if (id == null || id <= 0) {
            throw new CustomException("The id is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }

        if (userProjectDTO == null) {
            throw new CustomException("The 'role' field is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }
}
