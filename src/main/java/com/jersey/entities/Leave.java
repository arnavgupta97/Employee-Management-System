package com.jersey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	private int noOfLeavesApplied;
	private int noOfLeavesRemaining;
	private String status;
	private String startDate;
	private String details;
    private int employeeId;
	public Leave() {
		
	}
	public Leave(int employeeId, int noOfLeavesApplied, int noOfLeavesRemaining, String status, String startDate, String details) {
		this.noOfLeavesApplied = noOfLeavesApplied;
		this.noOfLeavesRemaining = noOfLeavesRemaining;
		this.status = status;
		this.startDate = startDate;
		this.employeeId = employeeId;
		this.details = details;
	}
    
	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
    
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public void setNoOfLeavesRemaining(int noOfLeavesRemaining) {
		this.noOfLeavesRemaining = noOfLeavesRemaining;
	}

	public int getNoOfLeavesApplied() {
		return noOfLeavesApplied;
	}

	public void setNoOfLeavesApplied(int noOfLeavesApplied) {
		this.noOfLeavesApplied = noOfLeavesApplied;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getNoOfLeavesRemaining() {
		return noOfLeavesRemaining;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", noOfLeavesApplied=" + noOfLeavesApplied + ", noOfLeavesRemaining="
				+ noOfLeavesRemaining + ", status=" + status + ", startDate=" + startDate + ", details=" + details + ", employeeId=" + employeeId +"]";
	}
	
}
