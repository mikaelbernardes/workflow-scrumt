package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.application.mapper.UserMapper;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.repository.UserRepository;
import com.workflow.scrumt.domain.useCase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(UserDTO user) {
        User newUser = userMapper.toEntity(user);

        return userRepository.save(newUser);
    }
}
