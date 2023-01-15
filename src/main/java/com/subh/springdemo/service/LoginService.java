package com.subh.springdemo.service;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dto.LoginRequestDTO;

public interface LoginService {

	public APIResponse login(LoginRequestDTO loginRequestDTO);
}
