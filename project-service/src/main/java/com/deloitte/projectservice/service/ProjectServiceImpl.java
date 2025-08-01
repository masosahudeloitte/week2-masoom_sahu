
package com.deloitte.projectservice.service;

import com.deloitte.projectservice.exception.ProjectNotFoundException;
import com.deloitte.projectservice.model.Project;
import com.deloitte.projectservice.repository.ProjectRepository;
import com.deloitte.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + id));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(int id, Project updated) {
        Project existing = getProjectById(id);
        existing.setProjectName(updated.getProjectName());
        existing.setProjectOwner(updated.getProjectOwner());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(int id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> getProjectsByOwner(int ownerId) {
        return projectRepository.findByProjectOwner(ownerId);
    }
    


    @Override
    public List<Object> getIssuesByProjectId(int projectId) {
        String url = "http://localhost:8083/api/issues/project/" + projectId;
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return response.getBody();
    }
    

    @Override
    public ResponseEntity<?> getIssuesByProjectName(String projectName) {
        Optional<Project> optionalProject = projectRepository.findByProjectName(projectName);

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
        }

        int projectId = optionalProject.get().getId();

        String url = "http://localhost:8083/api/issues/project/" + projectId;

        try {
            ResponseEntity<List> issuesResponse = restTemplate.getForEntity(url, List.class);
            return issuesResponse;
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
