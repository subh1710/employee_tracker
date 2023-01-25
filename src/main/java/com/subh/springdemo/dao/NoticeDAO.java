package com.subh.springdemo.dao;

import java.util.List;

import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.entity.Notice;

public interface NoticeDAO {

	public List<Notice> getNotices();

	public void saveNotice(Notice theNotice);

//	public Employee getEmployee(int theId);
//
//	public void deleteEmployee(int theId);
//
//	Employee getEmployeeBySecurityCode(String empSecurityCode);
}
