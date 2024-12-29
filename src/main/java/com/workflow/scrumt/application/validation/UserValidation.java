package com.workflow.scrumt.application.validation;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

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
    public void validateUpdate(UserDTO entity) {

    }

    @Override
    public void validateDelete(Long id) {

    }

    @Override
    public void validateRead(Long id) {

    }

}
