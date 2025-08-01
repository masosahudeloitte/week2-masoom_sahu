package com.deloitte.projectservice.controller;

import com.deloitte.projectservice.model.Project;
import com.deloitte.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Project project, @RequestParam int role) {
        if (role != 1) {
            return ResponseEntity.status(403).body("Only Project Owners can create projects.");
        }
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Project project, @RequestParam int role) {
        if (role != 1) {
            return ResponseEntity.status(403).body("Only Project Owners can update projects.");
        }
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, @RequestParam int role) {
        if (role != 1) {
            return ResponseEntity.status(403).body("Only Project Owners can delete projects.");
        }
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully.");
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Project>> getByOwner(@PathVariable int ownerId) {
        return ResponseEntity.ok(projectService.getProjectsByOwner(ownerId));
    }
    
    
    @GetMapping("/{projectId}/issues")
    public ResponseEntity<?> getIssuesByProjectId(@PathVariable int projectId) {
        List<Object> issues = projectService.getIssuesByProjectId(projectId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
    @GetMapping("/projectName/{projectName}/issues")
    public ResponseEntity<?> getIssuesByProjectName(@PathVariable String projectName) {
        return projectService.getIssuesByProjectName(projectName);
    }


    
}
