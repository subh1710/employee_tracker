package com.subh.springdemo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dao.AttendanceDAO;
import com.subh.springdemo.dao.EmployeeDAO;
import com.subh.springdemo.dto.AttendanceEntityResponse;
import com.subh.springdemo.entity.Attendance;
import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.rest.EmployeeNotFoundException;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private AttendanceDAO attendanceDAO;

	@Override
	@Transactional
	public APIResponse getAttendances(int employeeId) {
		APIResponse apiResponse = new APIResponse();
		List<AttendanceEntityResponse> attendanceList = attendanceDAO.getAttendances(employeeId);
		apiResponse.setData(attendanceList);
		return apiResponse;
	}

	@Override
	@Transactional
	public APIResponse saveAttendance(String securityCode) {

		APIResponse apiResponse = new APIResponse();

		Employee employee;
		try {
			employee = employeeDAO.getEmployeeBySecurityCode(securityCode);
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee not found with security code: " + securityCode);
		}

		LocalDateTime dateTime = LocalDateTime.now();
		LocalTime currentTime = dateTime.toLocalTime();
		LocalDate currentDate = dateTime.toLocalDate();
		String remarks = getRemarks(currentTime);
		Attendance attendance = new Attendance(currentDate, currentTime, remarks);
		employee.addAttendance(attendance);

		attendanceDAO.saveAttendance(attendance);
		apiResponse.setData("Attendance recorded successfully");
		return apiResponse;
	}

	private String getRemarks(LocalTime currentTime) {
		if (currentTime.compareTo(LocalTime.parse("09:00:00")) >= 0
				&& currentTime.compareTo(LocalTime.parse("09:15:00")) <= 0)
			return "On time";
		else if (currentTime.compareTo(LocalTime.parse("09:15:00")) > 0)
			return "Late entry";
		else
			return "Early entry";
	}
}
