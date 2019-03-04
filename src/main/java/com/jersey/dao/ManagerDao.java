package com.jersey.dao;

import java.util.List;

import javax.persistence.Query;
//import javax.ws.rs.core.Response;

import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.entities.Project;
import com.jersey.operations.Login;

public class ManagerDao {

	@SuppressWarnings("unchecked")
	public List<Leave> getLeaveList() {

		List<Leave> leaveList = null;
		try {
			String queryStr = "select leave from Leave leave";
			Query query = Login.session.createQuery(queryStr);
			leaveList = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return leaveList;
	}

	public Leave approveLeave(int leaveId) {

		Leave leave = Login.session.get(Leave.class, leaveId);
		System.out.println(leave.getStatus());
		
		int x = leave.getNoOfLeavesApplied();
		int y = leave.getNoOfLeavesRemaining();
		leave.setStatus("Approved");
		leave.setNoOfLeavesRemaining(x+y);
					
			if (Login.session.getTransaction().isActive()) {
				Login.session.update(leave);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.update(leave);
				Login.session.getTransaction().commit();
			}
		
		return leave;
	}

	public Leave rejectLeave(int leaveId) {

		Leave leave = Login.session.get(Leave.class, leaveId);
		System.out.println(leave);
		
		leave.setStatus("Rejected");
	
			if (Login.session.getTransaction().isActive()) {
				Login.session.update(leave);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.update(leave);
				Login.session.getTransaction().commit();
			}
		
		return leave;
	}

	public Project assignProject(String projectName, int projectManagerId, int employeeId) {
		Project project = new Project(projectName, projectManagerId, employeeId);

		Employee employee = Login.session.get(Employee.class, employeeId);

		if (employee != null) {

			(employee.getProject()).add(project);
			if (Login.session.getTransaction().isActive()) {
				Login.session.save(employee);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.save(employee);
				Login.session.getTransaction().commit();
			}
		} 
		
		return project;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjectList() {
		
		List<Project> projectList = null;
		try {
			String queryStr = "select project from Project project";
			Query query = Login.session.createQuery(queryStr);
			projectList = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return projectList;
	}
}
