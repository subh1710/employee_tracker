package com.subh.springdemo.service;

import java.util.List;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public void saveEmployee(Employee theEmployee);

	public Employee getEmployee(int theId);

	public void deleteEmployee(int theId);

	void updateEmployee(Employee theEmployee);

	public APIResponse getEmployeeLeaveDetailsByName(String fullName);

}
