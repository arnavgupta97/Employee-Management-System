package com.jersey.messenger;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.dao.AdminDao;
import com.jersey.entities.Employee;

@Path("admin")

public class AdminResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		AdminDao manager = new AdminDao();
		List<Employee> employeeList = manager.getEmployeeList();
		System.out.println(employeeList);
		return employeeList;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(Employee e) {
		AdminDao admin = new AdminDao();
		int id = e.getId();
		System.out.println("\nid:"+id);
		admin.delete(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addEmployee")
	public Employee addemployee(Employee employee){
		
		int id = employee.getId();
		String name = employee.getName();
		String password = employee.getPassword();
		String userrole = employee.getUserrole();
		int contact = employee.getContact();
		String email = employee.getEmail();
		
		System.out.println(id+" "+name+" "+password+" "+userrole+" "+contact+" "+email);
		
		AdminDao admin = new AdminDao();
		
		return admin.addEmployee(id,name,password,userrole,contact,email);
	}
	
}
