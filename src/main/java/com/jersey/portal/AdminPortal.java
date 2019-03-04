package com.jersey.portal;


import java.util.Scanner;

import com.jersey.dao.AdminDao;
import com.jersey.entities.Employee;
import com.jersey.operations.Login;

public class AdminPortal {

	public void loginAmdin() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		AdminDao admin = new AdminDao();
		try {
			int ch = 0;
			System.out.println("\n*********************************");
			System.out.println("Welcome to Admin portal");
			System.out.print("Enter User ID: ");
			int userid = scanner.nextInt();
			System.out.print("Enter Password: ");
			String password = scanner.next();
			Employee employee = Login.session.get(Employee.class, userid);
			do {
				if (employee != null && employee.getId() == userid && employee.getPassword().equals(password)
						&& employee.getUserrole().equals("administrator")) {
					System.out.println("\n************  Admin Menu *********************");
					System.out.println("1. Add Employee");
					System.out.println("2. Delete Employee");
					System.out.println("3. List All Employee");
					System.out.println("4. LogOut");
					System.out.println("*********************************");
					System.out.print("Enter Your Choice: ");
					ch = scanner.nextInt();
					switch (ch) {
					case 1:
						System.out.print("Enter Employee Id to Add: ");
						int id = scanner.nextInt();
						System.out.print("Enter Employee Name to Add: ");
						String name = scanner.next();
						System.out.print("Enter Employee Password to Add: ");
						String password2 = scanner.next();
						System.out.print("Enter Employee Role: ");
						String userRole = scanner.next();
						System.out.print("Enter Employee Contact to Add: ");
						int contact = scanner.nextInt();
						System.out.print("Enter Employee Email to Add: ");
						String email = scanner.next();
						System.out.println(admin.addEmployee(id,name,password2,userRole,contact,email));
						break;
					case 2:
						System.out.println("Enter Employee Id to Delete: ");
						int eid = scanner.nextInt();
						admin.delete(eid);
						break;
					case 3:
						System.out.println(admin.getEmployeeList());
						break;
					case 4:
						Login.getStarted();
						break;
					}

				} else {
					System.out.println("Wrong Username or Password");
					System.out.print("Do you want to try again Yes/No: ");
					String choice = scanner.next();
					if (choice.equals("Yes")) {
						System.out.println("\n*********************************");
						System.out.println("Welcome to Admin portal");
						System.out.print("Enter User ID: ");
						userid = scanner.nextInt();
						System.out.print("Enter Password: ");
						password = scanner.next();
					} else {
						return;
					}
				}
			} while (ch < 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
