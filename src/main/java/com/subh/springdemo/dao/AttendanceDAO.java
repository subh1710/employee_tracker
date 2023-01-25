package com.subh.springdemo.dao;

import java.util.List;

import com.subh.springdemo.dto.AttendanceEntityResponse;
import com.subh.springdemo.entity.Attendance;

public interface AttendanceDAO {

	public List<AttendanceEntityResponse> getAttendances(int employeeId);

	public void saveAttendance(Attendance theAttendance);

}
