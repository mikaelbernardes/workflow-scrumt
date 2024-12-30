package com.workflow.scrumt.application.validation;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

@Component
public class UserValidation extends EntityValidation<UserDTO> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    public void validateCreate(UserDTO user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new CustomException("The name is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getName().length() < 4 || user.getName().length() > 100) {
            throw new CustomException("The name cannot be shorter than 4 characters nor longer than 100 characters", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new CustomException("The email is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {
            throw new CustomException("The email is invalid.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new CustomException("The password is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword().length() < 8 || user.getPassword().length() > 100) {
            throw new CustomException("The user password cannot be shorter than 8 characters and cannot exceed 100 characters", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (!Pattern.matches(PASSWORD_REGEX, user.getPassword())) {
            throw new CustomException("The password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one number, and one special character.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validateUpdate(Long id, UserDTO user) {
        if (id == null || id <= 0) {
            throw new CustomException("The id is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new CustomException("The name is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getName().length() < 4 || user.getName().length() > 100) {
            throw new CustomException("The name cannot be shorter than 4 characters nor longer than 100 characters", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new CustomException("The email is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {
            throw new CustomException("The email is invalid.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() != null) {
            throw new CustomException("It is not allowed to change the password.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
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
                        throw new CustomException("The name is required and cannot be blank.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    if (((String) value).length() < 4 || ((String) value).length() > 100) {
                        throw new CustomException("The name cannot be shorter than 4 characters nor longer than 100 characters.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "email":
                    if (value == null || ((String) value).isBlank()) {
                        throw new CustomException("The email is required and cannot be blank.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    if (!Pattern.matches(EMAIL_REGEX, (String) value)) {
                        throw new CustomException("The email is invalid.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
                    }
                    break;
                default:
                    throw new CustomException("Invalid field: " + key, ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
            }
        });
    }

    @Override
    public void validateDelete(Long id) {

    }

    @Override
    public void validateRead(Long id) {
        if (id == null || id <= 0) {
            throw new CustomException("The id is required.", ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
        }
    }

}
