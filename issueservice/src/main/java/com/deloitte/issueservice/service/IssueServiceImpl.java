package com.deloitte.issueservice.service;


import com.deloitte.issueservice.exception.IssueNotFoundException;
import com.deloitte.issueservice.model.Issue;

import com.deloitte.issueservice.repository.IssueRepository;
import com.deloitte.issueservice.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private RestTemplate restTemplate;


    @Override
    public Issue createIssue(Issue issue) {
        issue.setCreatedOn(new Date());
        issue.setLastUpdatedOn(new Date());
        return issueRepository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getIssueById(int id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new IssueNotFoundException("Issue not found with ID: " + id));
    }

    @Override
    public Issue updateIssue(int id, Issue updatedIssue) {
        Issue existing = getIssueById(id);
        existing.setSummary(updatedIssue.getSummary());
        existing.setDescription(updatedIssue.getDescription());
        existing.setPriority(updatedIssue.getPriority());
        existing.setStatus(updatedIssue.getStatus());
        existing.setAssignee(updatedIssue.getAssignee());
        existing.setLastUpdatedOn(new Date());
        existing.setStoryPoint(updatedIssue.getStoryPoint());
        existing.setTag(updatedIssue.getTag());
        existing.setType(updatedIssue.getType());
        existing.setSprint(updatedIssue.getSprint());
        return issueRepository.save(existing);
    }

	@Override
	public List<Issue> getIssuesByOwnerId(int ownerId) {
		return issueRepository.findByCreatedBy(ownerId);
	}
    
	@Override
	public List<Issue> getIssuesByProjectId(int projectId) {
	    return issueRepository.findByProject(projectId);
	}
	@Override
	public List<Issue> getIssuesByAssignee(int assigneeId) {
	    return issueRepository.findByAssignee(assigneeId);
	}

	
	
	
    

}