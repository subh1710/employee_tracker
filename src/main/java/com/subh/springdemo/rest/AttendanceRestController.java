package com.subh.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.service.AttendanceService;

@RestController
@RequestMapping("/api")
public class AttendanceRestController {

	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/employee/{employeeId}/attendance")
	public ResponseEntity<APIResponse> getAttendances(@PathVariable("employeeId") Integer employeeId) {
		APIResponse apiResponse = attendanceService.getAttendances(employeeId);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
