package com.subh.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dto.LeaveRequestDTO;
import com.subh.springdemo.dto.NoticeRequestDTO;
import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.entity.Leave;
import com.subh.springdemo.entity.Notice;
import com.subh.springdemo.service.AttendanceService;
import com.subh.springdemo.service.EmployeeService;
import com.subh.springdemo.service.LeaveService;

@RestController
@RequestMapping("/api")
public class LeaveRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LeaveService leaveService;

	@GetMapping("/employee/{employeeId}/leave")
	public ResponseEntity<APIResponse> getLeaves(@PathVariable("employeeId") Integer employeeId) {
		APIResponse apiResponse = leaveService.getLeave(employeeId);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/employee/{employeeId}/leave")
	public ResponseEntity<APIResponse> addLeave(@RequestBody LeaveRequestDTO leaveRequestDTO,
			@PathVariable("employeeId") Integer employeeId) {
		Employee tmpEmployee = employeeService.getEmployee(employeeId);
		if (tmpEmployee == null) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
		}
		Leave theLeave = new Leave(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate(),
				leaveRequestDTO.getType(), leaveRequestDTO.getDetails(), "PENDING APPROVAL");
		tmpEmployee.addLeave(theLeave);
		APIResponse apiResponse = leaveService.addLeave(theLeave);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PutMapping("employee/{employeeId}/leave")
	public ResponseEntity<APIResponse> updateLeaveStatus(@RequestBody LeaveRequestDTO leaveRequestDTO,
			@PathVariable("employeeId") Integer employeeId) {
		Employee tmpEmployee = employeeService.getEmployee(employeeId);
		if (tmpEmployee == null) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
		}
		APIResponse apiResponse = leaveService.updateLeaveStatus(leaveRequestDTO.getId(),leaveRequestDTO.getApprovalStatus());
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
