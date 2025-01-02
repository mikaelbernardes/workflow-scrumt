package com.workflow.scrumt.domain.repository;

import com.workflow.scrumt.domain.entity.Project;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.entity.UserProject;
import com.workflow.scrumt.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
    long countByProjectIdAndRole(Long projectId, UserRole role);

    @Query("SELECT up.user FROM UserProject up WHERE up.project.id = :projectId")
    List<User> findUsersByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT up.project FROM UserProject up WHERE up.user.id = :userId")
    List<Project> findProjectsByUserId(@Param("userId") Long userId);

    boolean existsByProjectIdAndUserId(Long projectId, Long userId);

    UserProject findByProjectIdAndUserId(Long projectId, Long userId);
}
