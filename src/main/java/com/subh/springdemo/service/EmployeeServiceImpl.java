package com.subh.springdemo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dao.AttendanceDAO;
import com.subh.springdemo.dao.EmployeeDAO;
import com.subh.springdemo.dao.LeaveDAO;
import com.subh.springdemo.dto.AttendanceEntityResponse;
import com.subh.springdemo.dto.LeaveEntityResponse;
import com.subh.springdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private AttendanceDAO attendanceDAO;

	@Autowired
	private LeaveDAO leaveDAO;

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
		Employee tmpEmployee = employeeDAO.getEmployee(theEmployee.getId());
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

	@Override
	@Transactional
	public APIResponse getEmployeeLeaveDetailsByName(String fullName) {
		String firstName = "", lastName = "";
		fullName = fullName.trim();
		String[] arrOfStr = fullName.split("\\s+", 2);
		firstName = arrOfStr[0];
		lastName = arrOfStr[1];
		int empId = employeeDAO.getEmpIdByFirstNameAndLastName(firstName, lastName);
		APIResponse apiResponse = new APIResponse();
		List<AttendanceEntityResponse> attendanceList = attendanceDAO.getAttendances(empId);
		List<LeaveEntityResponse> LeaveList = leaveDAO.getLeaves(empId);
		Map<String, Object> data = new HashMap<>();
		data.put("Attendance", attendanceList);
		data.put("Leaves", LeaveList);
		data.put("Id",empId);
		apiResponse.setData(data);
		return apiResponse;
	}

}
