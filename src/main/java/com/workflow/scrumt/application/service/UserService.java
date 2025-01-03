package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.application.mapper.UserMapper;
import com.workflow.scrumt.application.validation.UserValidation;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.exceptions.CustomException;
import com.workflow.scrumt.domain.exceptions.ExceptionLevel;
import com.workflow.scrumt.domain.repository.UserRepository;
import com.workflow.scrumt.domain.useCase.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService implements
        CreateUserUseCase,
        UpdateUserUseCase,
        PatchUserUseCase,
        FindUserByIdUseCase,
        DeleteUserUseCase
{

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
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        System.out.println("==================> " + encryptedPassword);
        User newUser = userMapper.toEntity(user);
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long id, UserDTO user) {
        userValidation.validateUpdate(id, user);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));

        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("Email already exists.", ExceptionLevel.ERROR, HttpStatus.CONFLICT);
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    @Override
    public User patchUser(Long id, Map<String, Object> updates) {
        userValidation.validatePatch(id, updates);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingUser.setName((String) value);
                    break;
                case "email":
                    if (userRepository.existsByEmail((String) value)) {
                        throw new CustomException("Email already exists.", ExceptionLevel.ERROR, HttpStatus.CONFLICT);
                    }
                    existingUser.setEmail((String) value);
                    break;
                default:
                    throw new CustomException("Invalid field: " + key, ExceptionLevel.ERROR, HttpStatus.BAD_REQUEST);
            }
        });

        return userRepository.save(existingUser);
    }

    @Override
    public User findUserById(Long id) {
        userValidation.validateRead(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteUser(Long id) {
        userValidation.validateDelete(id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", ExceptionLevel.ERROR, HttpStatus.NOT_FOUND));
        userRepository.deleteById(existingUser.getId());
    }
}
