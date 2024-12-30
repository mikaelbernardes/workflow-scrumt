package com.workflow.scrumt.application.mapper;

import com.workflow.scrumt.application.dto.ProjectDTO;
import com.workflow.scrumt.application.dto.UserDTO;
import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project project);
    Project toEntity(ProjectDTO projectDTO);
}
