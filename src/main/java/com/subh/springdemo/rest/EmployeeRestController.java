package com.subh.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.subh.springdemo.entity.Employee;
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
}
