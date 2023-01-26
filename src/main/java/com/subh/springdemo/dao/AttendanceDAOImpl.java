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
import com.subh.springdemo.entity.Attendance;

@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AttendanceEntityResponse> getAttendances(int employeeId) {
		Session currentSession = sessionFactory.getCurrentSession();
		SQLQuery query = currentSession.createSQLQuery(
				"select attendance.* from attendance, (select date, max(time) as max_time from attendance group by date) a where attendance.date=a.date and attendance.time=a.max_time and employee_master_id=:employeeId order by date desc");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("employeeId", employeeId);
		List data = query.list();
		AttendanceEntityResponse attendanceEntityResponse = null;
		Map row = null;
		List<AttendanceEntityResponse> attendanceEntityResponseList = new ArrayList<>();
		for (Object object : data) {
			attendanceEntityResponse = new AttendanceEntityResponse();
			row = (Map) object;
			attendanceEntityResponse.setDate(String.valueOf(row.get("date")));
			attendanceEntityResponse.setTime(String.valueOf(row.get("time")));
			attendanceEntityResponse.setRemarks((String) row.get("remarks"));
			attendanceEntityResponseList.add(attendanceEntityResponse);
		}

		return attendanceEntityResponseList;
	}

	@Override
	public void saveAttendance(Attendance theAttendance) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theAttendance);
	}

}
