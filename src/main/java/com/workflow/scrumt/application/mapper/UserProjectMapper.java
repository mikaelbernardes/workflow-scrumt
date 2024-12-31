package com.workflow.scrumt.application.mapper;

import com.workflow.scrumt.application.dto.UserProjectDTO;
import com.workflow.scrumt.domain.entity.UserProject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProjectMapper {

    UserProjectDTO toDTO(UserProject userProject);
    UserProject toEntity(UserProjectDTO userProjectDTO);
}
