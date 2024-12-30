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
        if (project.getName().length() < 2 || project.getName().length() > 150) {
            throw new CustomException("The project name cannot be shorter than 2 characters nor longer than 150 characters", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validateUpdate(Long id, ProjectDTO project) {
        if (id == null || id <= 0) {
            throw new CustomException("The id is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (project.getName() == null || project.getName().isBlank()) {
            throw new CustomException("The project name is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (project.getName().length() < 2 || project.getName().length() > 150) {
            throw new CustomException("The project name cannot be shorter than 2 characters nor longer than 150 characters", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validatePatch(Long id, Map<String, Object> updates) {
        if (id == null || id <= 0) {
            throw new CustomException("The id is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (updates == null || updates.isEmpty()) {
            throw new CustomException("At least one field is required for update.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    if (value == null || ((String) value).isBlank()) {
                        throw new CustomException("The project name is required and cannot be blank.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    if (((String) value).length() < 2 || ((String) value).length() > 100) {
                        throw new CustomException("The project name cannot be shorter than 2 characters nor longer than 100 characters.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "description":
                    if (value == null) {
                        throw new CustomException("The description is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    break;
                default:
                    throw new CustomException("Invalid field: " + key, ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
            }
        });
    }

    @Override
    public void validateDelete(Long id) {
        if (id == null || id <= 0) {
            throw new CustomException("The id is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validateRead(Long id) {

    }
}
