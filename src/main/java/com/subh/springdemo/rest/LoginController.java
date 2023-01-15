package com.subh.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dto.LoginRequestDTO;
import com.subh.springdemo.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = loginService.login(loginRequestDTO);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
