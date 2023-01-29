package com.subh.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dao.LeaveDAO;
import com.subh.springdemo.dto.LeaveEntityResponse;
import com.subh.springdemo.entity.Leave;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveDAO leaveDAO;

	@Override
	@Transactional
	public APIResponse getLeave(int employeeId) {
		APIResponse apiResponse = new APIResponse();
		List<LeaveEntityResponse> LeaveList = leaveDAO.getLeaves(employeeId);
		apiResponse.setData(LeaveList);
		return apiResponse;
	}

	@Override
	@Transactional
	public APIResponse addLeave(Leave theLeave) {

		APIResponse apiResponse = new APIResponse();
		leaveDAO.addLeave(theLeave);
		apiResponse.setData("Leave request added successfully");
		return apiResponse;
	}
	
	@Override
	@Transactional
	public APIResponse updateLeaveStatus(int leaveId, String status) {
		APIResponse apiResponse = new APIResponse();
		leaveDAO.updateLeaveStatus(leaveId,status);
		apiResponse.setData("Leave approval status updated successfully");
		return apiResponse;
	}

}
