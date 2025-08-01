package com.deloitte.projectservice.service;

import com.deloitte.projectservice.model.Project;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ProjectService {
    Project createProject(Project project);
    Project getProjectById(int id);
    List<Project> getAllProjects();
    Project updateProject(int id, Project updatedProject);
    void deleteProject(int id);
    List<Project> getProjectsByOwner(int ownerId);
    public List<Object> getIssuesByProjectId(int projectId);

     ResponseEntity<?> getIssuesByProjectName(String projectName);
}

