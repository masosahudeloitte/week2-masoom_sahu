package com.deloitte.projectservice.repository;

import com.deloitte.projectservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByProjectOwner(int ownerId);
    Optional<Project> findByProjectName(String projectName);
}