package com.subh.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.entity.Notice;
import com.subh.springdemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
		}
		return employee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.saveEmployee(employee);
		return employee;
	}

	@PutMapping("/employees")
	public Employee updateStudent(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return employee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteCustomer(@PathVariable int employeeId) {
		Employee tempStudent = employeeService.getEmployee(employeeId);
		if (tempStudent == null) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
		}
		employeeService.deleteEmployee(employeeId);
		return "Employee deleted successfully with ID: " + employeeId;
	}

	@GetMapping("/employee/{adminId}/search/{employeeName}")
	public ResponseEntity<APIResponse> getEmployeeLeaveDetailsByName(@PathVariable int adminId,
			@PathVariable String employeeName) {
		Employee employee = employeeService.getEmployee(adminId);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + adminId);
		}
		if (!employee.isAdmin()) {
			throw new EmployeeNotFoundException("Access denied! Only an admin is allowed to perform this operation!!");
		}
		APIResponse apiResponse = employeeService.getEmployeeLeaveDetailsByName(employeeName);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
