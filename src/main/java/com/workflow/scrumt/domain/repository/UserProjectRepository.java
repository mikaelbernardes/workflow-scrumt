package com.workflow.scrumt.domain.repository;

import com.workflow.scrumt.domain.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
}
