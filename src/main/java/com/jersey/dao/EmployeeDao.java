package com.jersey.dao;

import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.operations.Login;

public class EmployeeDao {

	public Employee updateDetails(int id, String name, String password, int contact, String email) {

		Employee employee = Login.session.get(Employee.class, id);

		employee.setName(name);
		employee.setPassword(password);
		employee.setContact(contact);
		employee.setEmail(email);

		if (Login.session.getTransaction().isActive()) {
			Login.session.update(employee);
			Login.session.getTransaction().commit();
		} else {
			Login.session.beginTransaction();
			Login.session.update(employee);
			Login.session.getTransaction().commit();
		}
		return employee;
	}

	public Leave cancelLeave(int leaveId) {

		Leave leave = Login.session.get(Leave.class, leaveId);
		leave.setStatus("Cancelled");

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

	public Leave applyForLeave(int id, int days, String startDate, String details) {

		Employee employee = Login.session.get(Employee.class, id);
		int x=0;
		if(employee.getLeave().size()!=0) {
		for(Leave l : employee.getLeave()) {
			if(l.getStatus()=="Approved") {
				x=Integer.max(x,l.getNoOfLeavesRemaining());	
			}
		}
		}
		Leave leave = new Leave(id, days, x, "Pending", startDate,details);
		System.out.println(leave);
		
		if(employee!=null) {
		(employee.getLeave()).add(leave);
		if (Login.session.getTransaction().isActive()) {
			Login.session.save(employee);
			Login.session.getTransaction().commit();
		} else {
			Login.session.beginTransaction();
			Login.session.save(employee);
			Login.session.getTransaction().commit();
		}
		}
		return leave;
	}
	}
