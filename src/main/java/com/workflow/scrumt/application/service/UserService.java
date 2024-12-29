package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.application.mapper.UserMapper;
import com.workflow.scrumt.application.validation.UserValidation;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import com.workflow.scrumt.domain.repository.UserRepository;
import com.workflow.scrumt.domain.useCase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService implements CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserValidation userValidation;

    @Override
    public User createUser(UserDTO user) {
        userValidation.validateCreate(user);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("Email already exists.", ExceptionLevel.ERROR, HttpStatus.CONFLICT);
        }
        user.setCreatedAt(LocalDateTime.now());
        User newUser = userMapper.toEntity(user);
        return userRepository.save(newUser);
    }
}
