package com.subh.springdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee_master")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "is_admin")
	private boolean isAdmin;

	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "employee_login_manager_id")
	private EmployeeLoginManager employeeLoginManager;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JsonIgnore
	private List<Notice> notices;

	@OneToMany(mappedBy = "employeeAttendance", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JsonIgnore
	private List<Attendance> attendances;

	public Employee() {
	}

	public Employee(int id, String firstName, String lastName, String email, boolean isAdmin,
			EmployeeLoginManager employeeLoginManager) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public EmployeeLoginManager getEmployeeLoginManager() {
		return employeeLoginManager;
	}

	public void setEmployeeLoginManager(EmployeeLoginManager employeeLoginManager) {
		this.employeeLoginManager = employeeLoginManager;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public void addNotice(Notice tempNotice) {
		if (notices == null) {
			notices = new ArrayList<>();
		}
		notices.add(tempNotice);
		tempNotice.setEmployee(this);
	}

	public void addAttendance(Attendance tempAttendance) {
		if (attendances == null) {
			attendances = new ArrayList<>();
		}
		attendances.add(tempAttendance);
		tempAttendance.setEmployeeAttendance(this);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", isAdmin=" + isAdmin + ", employeeLoginManager=" + employeeLoginManager + "]";
	}

}
