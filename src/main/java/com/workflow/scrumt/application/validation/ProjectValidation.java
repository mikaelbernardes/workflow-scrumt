package com.workflow.scrumt.application.validation;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProjectValidation extends EntityValidation<ProjectDTO> {
    @Override
    public void validateCreate(ProjectDTO project) {
        if (project.getName() == null || project.getName().isBlank()) {
            throw new CustomException("The project name is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validateUpdate(Long id, ProjectDTO entity) {

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
}
