package com.deloitte.issueservice.repository;



import com.deloitte.issueservice.model.Issue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
	
	List<Issue> findByCreatedBy(int ownerId);
	
	List<Issue> findByProject(int projectId);
	List<Issue> findByAssignee(int assigneeId);
}

