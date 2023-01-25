package com.subh.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dto.LoginRequestDTO;
import com.subh.springdemo.service.AttendanceService;
import com.subh.springdemo.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO, @RequestParam("isSecurityPortal") Boolean isSecurityPortal) {

		APIResponse apiResponse;
		
		if(isSecurityPortal) {
			apiResponse =attendanceService.saveAttendance(loginRequestDTO.getSecurityCode());
		}
		else {
			 apiResponse = loginService.login(loginRequestDTO);
		}

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
