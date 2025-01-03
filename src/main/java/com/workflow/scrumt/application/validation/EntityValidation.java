package com.workflow.scrumt.application.validation;

import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class EntityValidation<T> {

    protected void validateRequiredFields(T entity) {
        if (entity == null) {
            throw new CustomException("The entity is required", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    public abstract void validateCreate(T entity);

    public abstract void validateUpdate(Long id, T entity);

    public abstract void validatePatch(Long id, Map<String, Object> updates);

    public abstract void validateDelete(Long id);

    public abstract void validateRead(Long id);
}
