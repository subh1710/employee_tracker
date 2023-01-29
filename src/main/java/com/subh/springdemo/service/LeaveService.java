package com.subh.springdemo.service;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.entity.Leave;

public interface LeaveService {

	public APIResponse getLeave(int employeeId);

	public APIResponse addLeave(Leave theLeave);
	
	public APIResponse updateLeaveStatus(int leaveId, String status);

}
