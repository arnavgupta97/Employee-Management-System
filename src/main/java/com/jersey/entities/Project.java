package com.jersey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String projectName;
	private int projectManagerId;
	private int employeeId;

	public Project() {		
	}
	
    public Project(String projectName, int projectManagerId, int employeeId ) {
		this.projectName = projectName;
		this.projectManagerId = projectManagerId;
		this.employeeId = employeeId;
	}

    
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(int projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectManagerId="
				+ projectManagerId + ", employeeId="
						+ employeeId +"]";
	}
	
}
