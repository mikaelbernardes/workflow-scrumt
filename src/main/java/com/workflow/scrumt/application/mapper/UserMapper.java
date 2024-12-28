package com.workflow.scrumt.application.mapper;

import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
