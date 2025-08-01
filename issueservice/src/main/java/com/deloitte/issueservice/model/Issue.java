package com.deloitte.issueservice.model;

import com.deloitte.issueservice.model.enums.Priority;
import com.deloitte.issueservice.model.enums.Status;
import com.deloitte.issueservice.model.enums.Type;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String summary;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int assignee;
    private int project;
    private int createdBy;

    private Date createdOn;
    private Date lastUpdatedOn;

    private int storyPoint;
    private String tag;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String sprint;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getAssignee() {
		return assignee;
	}

	public void setAssignee(int assignee) {
		this.assignee = assignee;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public int getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

  
}
