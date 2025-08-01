package com.deloitte.issueservice.controller;

import com.deloitte.issueservice.model.Issue;
import com.deloitte.issueservice.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    public ResponseEntity<Issue> create(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.createIssue(issue));
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAll() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getById(@PathVariable int id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> update(@PathVariable int id, @RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.updateIssue(id, issue));
    }
    
    

@GetMapping("/owner/{ownerId}")
public ResponseEntity<?> getIssuesByOwnerId(@PathVariable int ownerId) {
    try {
        List<Issue> issues = issueService.getIssuesByOwnerId(ownerId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
@GetMapping("/project/{projectId}")
public ResponseEntity<List<Issue>> getIssuesByProject(@PathVariable int projectId) {
    List<Issue> issues = issueService.getIssuesByProjectId(projectId);
    return new ResponseEntity<>(issues, HttpStatus.OK);
}


@GetMapping("/assignee/{assigneeId}")
public ResponseEntity<List<Issue>> getIssuesByAssignee(@PathVariable int assigneeId) {
    List<Issue> issues = issueService.getIssuesByAssignee(assigneeId);
    return new ResponseEntity<>(issues, HttpStatus.OK);
}

}
