package com.subh.springdemo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.subh.springdemo.dto.AttendanceEntityResponse;
import com.subh.springdemo.dto.LeaveEntityResponse;
import com.subh.springdemo.entity.Attendance;
import com.subh.springdemo.entity.Leave;

@Repository
public class LeaveDAOImpl implements LeaveDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LeaveEntityResponse> getLeaves(int employeeId) {
		Session currentSession = sessionFactory.getCurrentSession();
		SQLQuery query = currentSession.createSQLQuery(
				"select id,start_date, end_date, type, details, approval_status from leave_master where employee_master_id=:employeeId order by start_date desc");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("employeeId", employeeId);
		List data = query.list();
		LeaveEntityResponse leaveEntityResponse = null;
		Map row = null;
		List<LeaveEntityResponse> leaveEntityResponseList = new ArrayList<>();
		for (Object object : data) {
			leaveEntityResponse = new LeaveEntityResponse();
			row = (Map) object;
			leaveEntityResponse.setId(Integer.parseInt(String.valueOf(row.get("id"))));
			leaveEntityResponse.setStartDate(String.valueOf(row.get("start_date")));
			leaveEntityResponse.setEndDate(String.valueOf(row.get("end_date")));
			leaveEntityResponse.setType((String) row.get("type"));
			leaveEntityResponse.setDetails((String) row.get("details"));
			leaveEntityResponse.setApprovalStatus((String) row.get("approval_status"));
			leaveEntityResponseList.add(leaveEntityResponse);
		}

		return leaveEntityResponseList;
	}

	@Override
	public void addLeave(Leave theLeave) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theLeave);
	}

	@Override
	public void updateLeaveStatus(int leaveId, String status) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.createQuery("update Leave set approvalStatus=:status where id=:leaveId")
				.setParameter("status", status).setParameter("leaveId", leaveId).executeUpdate();
	}

}
