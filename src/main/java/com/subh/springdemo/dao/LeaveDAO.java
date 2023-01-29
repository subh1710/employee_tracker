package com.subh.springdemo.dao;

import java.util.List;

import com.subh.springdemo.dto.LeaveEntityResponse;
import com.subh.springdemo.entity.Leave;

public interface LeaveDAO {

	public List<LeaveEntityResponse> getLeaves(int employeeId);

	public void addLeave(Leave theLeave);
	
	public void updateLeaveStatus(int leaveId, String status);

}
