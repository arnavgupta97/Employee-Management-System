package com.jersey.portal;

import java.util.Scanner;

import com.jersey.dao.EmployeeDao;
import com.jersey.entities.Employee;
import com.jersey.operations.Login;

public class EmployeePortal {

	public void loginEmployee() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		EmployeeDao employeeDao = new EmployeeDao();
		try {
			int ch = 0;
			System.out.println("Welcome to Employee portal");
			System.out.print("Enter User ID: ");
			int userid = scanner.nextInt();
			System.out.print("Enter Password: ");
			String password = scanner.next();
			Employee employee = Login.session.get(Employee.class, userid);
			do {
				if (employee!=null && employee.getId() == userid && employee.getPassword().equals(password)
						&& employee.getUserrole().equals("employee")) {

					System.out.println("\n************  Employee Menu *********************");
					System.out.println("1. Apply For Leave");
					System.out.println("2. Cancel Leave");
					System.out.println("3. Leave Status");
					System.out.println("4. Remaining Leaves");
					System.out.println("5. Update Details");
					System.out.println("6. LogOut");
					System.out.print("Enter Your Choice: ");
					ch = scanner.nextInt();

					switch (ch) {
					case 1: {
//						System.out.println("Enter New Name: ");
//						int days = scanner.nextInt();
//						System.out.println("Enter New Password: ");
//						String startDate = scanner.next();
//						System.out.println(employeeDao.applyForLeave(userid, days, startDate));
						break;
					}
					case 2:
//						employeeDao.cancelLeave(userid);
						break;
					case 3:
//						System.out.println(employeeDao.leaveDetails(userid));
						break;
					case 4:
//						System.out.println(employeeDao.leaveDetails(userid));
						break;
					case 5:
						System.out.println("Enter New Name: ");
						String name = scanner.next();
						System.out.println("Enter New Password: ");
						String password2 = scanner.next();
						System.out.println("Enter New Contact: ");
						int contact = scanner.nextInt();
						System.out.println("Enter New Email: ");
						String email = scanner.next();
						
						System.out.println("Updated Status: "+employeeDao.updateDetails(userid, name, password2, contact, email));
						break;
					case 6:
						Login.getStarted();
						break;
					}
				} else {

					System.out.print("Do you want to try again Yes/No: ");
					String choice = scanner.next();
					if (choice.equals("Yes")) {
						System.out.println("Welcome to Employee portal");
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
