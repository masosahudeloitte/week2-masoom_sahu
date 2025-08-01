package com.deloitte.projectservice.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String projectName;
    private int projectOwner;
    private Date startDate;
    private Date endDate;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public int getProjectOwner() { return projectOwner; }
    public void setProjectOwner(int projectOwner) { this.projectOwner = projectOwner; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
