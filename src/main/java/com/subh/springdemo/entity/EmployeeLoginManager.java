package com.subh.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee_login_manager")
public class EmployeeLoginManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private int id;

	@Column(name = "emp_security_code")
	private String empSecurityCode;

	public EmployeeLoginManager() {
	}

	public EmployeeLoginManager(int id, String empSecurityCode) {
		this.empSecurityCode = empSecurityCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpSecurityCode() {
		return empSecurityCode;
	}

	public void setEmpSecurityCode(String empSecurityCode) {
		this.empSecurityCode = empSecurityCode;
	}

	@Override
	public String toString() {
		return "EmployeeLoginManager [id=" + id + ", empSecurityCode=" + empSecurityCode + "]";
	}

}
