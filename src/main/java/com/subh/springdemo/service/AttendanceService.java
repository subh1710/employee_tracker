package com.subh.springdemo.service;

import com.subh.springdemo.common.APIResponse;

public interface AttendanceService {

	public APIResponse getAttendances(int employeeId);

	public APIResponse saveAttendance(String securityCode);

}
