package com.jersey.datasource;

import java.util.ArrayList;
import java.util.HashMap;

import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.entities.Project;

public class DataSource {

	public static ArrayList<Employee> employeeList = new ArrayList<>();
	
	public static HashMap<Integer, Leave> leaveList = new HashMap<>();

	public static HashMap<Employee, Project> projectList = new HashMap<>();
	
}
