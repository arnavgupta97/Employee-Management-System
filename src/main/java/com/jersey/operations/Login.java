package com.jersey.operations;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jersey.constants.Constants;
import com.jersey.entities.Employee;
import com.jersey.portal.AdminPortal;
import com.jersey.portal.EmployeePortal;
import com.jersey.portal.ManagerPortal;

public class Login {

	public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session session = sessionFactory.openSession();
	
	public static void main(String[] args) {
		getStarted();	
	}

	public static void getStarted() {
		Scanner scanner = new Scanner(System.in);
		AdminPortal adminPortal = new AdminPortal();
		ManagerPortal managerPortal = new ManagerPortal();
		EmployeePortal employeePortal = new EmployeePortal();

		int ch;
		try {
			do {
				System.out.println("\n*********************************");
				System.out.println("1. Login as an Admin");
				System.out.println("2. Login as a Manager");
				System.out.println("3. Login as an Employee");
				System.out.println("4. Exit");
				System.out.println("*********************************");
				System.out.print("Enter your choice : ");
				ch = scanner.nextInt();
				switch (ch) {
				case 1:
					Employee employee = session.get(Employee.class, 99999);
					if (employee == null) {
						Employee admin = new Employee(99999, "admin", "admin", Constants.ADMIN, 452145,
								"admin@abyeti.com");
						if (!session.getTransaction().isActive()) {
							session.beginTransaction();
						}
						session.save(admin);
						session.getTransaction().commit();
					}
					adminPortal.loginAmdin();
					break;
				case 2:
					Employee employee1 = session.get(Employee.class, 88888);
					if (employee1 == null) {
						Employee manager = new Employee(88888, "manager1", "manager1", Constants.MANAGER, 453145,
								"manager@abyeti.com");
						if (!session.getTransaction().isActive()) {
							session.beginTransaction();
						}
						session.save(manager);
						session.getTransaction().commit();
					}
					managerPortal.loginManager();
					break;
				case 3:
					employeePortal.loginEmployee();
					break;
				case 4:
					System.exit(0);
					break;
				}
			} while (ch < 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}
}
