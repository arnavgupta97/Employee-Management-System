package com.jersey.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	private int id;
	private String userrole;
	private String name;
	private int contact;
	private String password;
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Leave> leave;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Project> project = new HashSet<>();
	
	public Employee() {
		
	}
	public Employee(int id, String name, String password, String userrole, int contact, String email) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.password = password;
		this.email = email;
		this.userrole = userrole;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Leave> getLeave() {
		return leave;
	}

	public void setLeave(Set<Leave> leave) {
		this.leave = leave;
	}

	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public Set<Project> getProject() {
		return project;
	}

	public void setProject(Set<Project> project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", userRole=" + userrole + ", name=" + name + ", contact=" + contact
				+ ", password=" + password + ", email=" + email + ", leave=" + leave + ", project=" + project + "]";
	}
	
}
