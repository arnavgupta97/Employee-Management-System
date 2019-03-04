package com.jersey.portal;

import java.util.Scanner;

import com.jersey.dao.ManagerDao;
import com.jersey.entities.Employee;
import com.jersey.operations.Login;

public class ManagerPortal {

	public void loginManager() {
		Login.session.beginTransaction();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ManagerDao managerDao = new ManagerDao();
		try {
			int ch = 0;
			System.out.println("WELCOME TO MANAGER PORTAL");
			System.out.print("Enter User ID: ");
			int userid = scanner.nextInt();
			System.out.print("Enter Password: ");
			String password = scanner.next();
			Employee manager = Login.session.get(Employee.class, userid);
			do {
				if (manager!=null && manager.getId() == userid && manager.getPassword().equals(password)
						&& manager.getUserrole().equals("manager")) {

					System.out.println("*****HOME PAGE*****");
					System.out.println("1. List All Leaves");
					System.out.println("2. Approve Leave");
					System.out.println("3. Reject Leave");
					System.out.println("4. List All Projects Assigned");
					System.out.println("5. Assign Project");
					System.out.println("6. Logout");
					System.out.print("Enter Your Choice: ");
					ch = scanner.nextInt();

					switch (ch) {
					case 1:
                        System.out.println(managerDao.getLeaveList());
						break;
					case 2:
//						managerDao.approveLeave();
						break;
					case 3:
//						managerDao.rejectLeave();
						break;
					case 4:
						System.out.println(managerDao.getProjectList());
						break;
					case 5:
						System.out.print("Enter Project Name to Assign: ");
						String projectName = scanner.next();
						System.out.println("Enter Project Manager ID to be Assigned: ");
						int projectManagerId = scanner.nextInt();
						System.out.print("Enter Employee ID to Assigned: ");
						int empId = scanner.nextInt();
						managerDao.assignProject(projectName, projectManagerId, empId);
						break;
					case 6:
						Login.getStarted();
						break;
					}
				} else {
					System.out.println("Wrong Username or Password");
					System.out.print("Do you want to try again Yes/No: ");
					String choice = scanner.next();
					if (choice.equals("Yes")) {
						System.out.println("WELCOME TO MANAGER PORTAL");
						System.out.println("Enter Username: ");
						userid = scanner.nextInt();
						System.out.println("Enter Password: ");
						password = scanner.next();
					} else {
						return;
					}
				}
			} while (ch < 7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
