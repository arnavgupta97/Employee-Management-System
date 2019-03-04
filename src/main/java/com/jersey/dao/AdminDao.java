package com.jersey.dao;

import java.util.List;

import javax.persistence.Query;

import com.jersey.entities.Employee;
import com.jersey.operations.Login;

public class AdminDao {

	public Employee addEmployee(int id, String name, String password, String userrole, int contact, String email) {
		Employee employee = new Employee(id, name, password, userrole, contact, email);
		
		System.out.println(employee.toString());

		if (Login.session.getTransaction().isActive()) {
			Login.session.save(employee);
			Login.session.getTransaction().commit();
		} else {
			Login.session.beginTransaction();
			Login.session.save(employee);
			Login.session.getTransaction().commit();
		}
		return employee;
	}

	public void delete(int id) {
	
		Employee employee = Login.session.get(Employee.class, id);
		if(employee==null) {
			return ;
		}
		if (employee.getId() == id) {
			if (Login.session.getTransaction().isActive()) {
				Login.session.delete(employee);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.delete(employee);
				Login.session.getTransaction().commit();
			}
		}
		return ;
	}

	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList() {

		List<Employee> employeeList = null;
		try {
			String queryStr = "select employee from Employee employee";
			Query query = Login.session.createQuery(queryStr);
			employeeList = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return employeeList;
	}

	public Employee logIn(int id) {
		Employee employee = Login.session.get(Employee.class, id);
		if(employee==null) {
			return new Employee(0,"","","",0,"");
		}
		return employee;
	}
}
