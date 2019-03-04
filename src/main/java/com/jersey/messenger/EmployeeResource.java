package com.jersey.messenger;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.dao.AdminDao;
import com.jersey.dao.EmployeeDao;
import com.jersey.entities.Employee;
import com.jersey.entities.Leave;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		AdminDao manager = new AdminDao();
		List<Employee> employeeList = manager.getEmployeeList();
		return employeeList;
	}

	@PUT
	@Path("applyLeave")
	public Leave applyLeaves(Employee empl) {
		EmployeeDao employee = new EmployeeDao();
		int id = empl.getId();
		Leave leave=null;
		for(Leave l : empl.getLeave()) {
			leave=l;
			break;
		}
		int days = leave.getNoOfLeavesApplied();
		String startDate = leave.getStartDate();
		String details = leave.getDetails();
		System.out.println(id);
		System.out.println(leave);
		return employee.applyForLeave(id, days, startDate,details);
	}

	@PUT
	@Path("cancel")
	public Leave cancelLeaves(Leave leave) {
		EmployeeDao employee = new EmployeeDao();
		int leaveId = leave.getLeaveId();
		System.out.println(leaveId);
		return employee.cancelLeave(leaveId);
	}

    @PUT
	@Path("update")
	public Employee updateDetail(Employee employee){
		EmployeeDao empl = new EmployeeDao();
		
		int id = employee.getId();
		String name = employee.getName();
		String password = employee.getPassword();
		int contact = employee.getContact();
		String email = employee.getEmail();
		
		return empl.updateDetails(id,name,password,contact,email);
	}
}
