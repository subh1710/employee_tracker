package com.subh.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subh.springdemo.dao.EmployeeDAO;
import com.subh.springdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		employeeDAO.saveEmployee(theEmployee);
	}
	
	@Override
	@Transactional
	public void updateEmployee(Employee theEmployee) {
		Employee tmpEmployee=employeeDAO.getEmployee(theEmployee.getId());
		tmpEmployee.setFirstName(theEmployee.getFirstName());
		tmpEmployee.setLastName(theEmployee.getLastName());
		tmpEmployee.setEmail(theEmployee.getEmail());
		tmpEmployee.setAdmin(theEmployee.isAdmin());
		tmpEmployee.setEmployeeLoginManager(theEmployee.getEmployeeLoginManager());
		employeeDAO.saveEmployee(tmpEmployee);
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {
		return employeeDAO.getEmployee(theId);
	}

	@Override
	@Transactional
	public void deleteEmployee(int theId) {
		employeeDAO.deleteEmployee(theId);
	}

}
