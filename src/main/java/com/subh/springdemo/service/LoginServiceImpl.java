package com.subh.springdemo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dao.EmployeeDAO;
import com.subh.springdemo.dto.LoginRequestDTO;
import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.rest.EmployeeNotFoundException;
import com.subh.springdemo.util.JwtUtils;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
    private JwtUtils jwtUtils;

	@Override
	@Transactional
	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		
		 APIResponse apiResponse = new APIResponse();

	        // validation

	        // verify user exist with given email and password
		 Employee employee;
		try {
			employee = employeeDAO.getEmployeeBySecurityCode(loginRequestDTO.getSecurityCode());
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee not found with security code: " + loginRequestDTO.getSecurityCode());		
			}

	        // response
//	        if(employee == null){
//	            apiResponse.setData("User login failed");
//	            return apiResponse;
//	        }

	        // generate jwt
	        String token = jwtUtils.generateJwt(employee);
//
	        Map<String , Object> data = new HashMap<>();
	        data.put("employee", employee);
	        data.put("accessToken", token);

	        apiResponse.setData(data);

	        return apiResponse;
	}

}
