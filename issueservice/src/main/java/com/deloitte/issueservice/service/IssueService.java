package com.deloitte.issueservice.service;


import com.deloitte.issueservice.model.Issue;
import java.util.List;

public interface IssueService {
    Issue createIssue(Issue issue);
    List<Issue> getAllIssues();
    Issue getIssueById(int id);
    Issue updateIssue(int id, Issue updatedIssue);
    List<Issue> getIssuesByOwnerId(int ownerId);
    List<Issue> getIssuesByProjectId(int projectId);

List<Issue> getIssuesByAssignee(int assigneeId);

}
