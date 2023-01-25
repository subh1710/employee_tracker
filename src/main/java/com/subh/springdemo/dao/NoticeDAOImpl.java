package com.subh.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.subh.springdemo.entity.Notice;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Notice> getNotices() {
		Session currentSession = sessionFactory.getCurrentSession();
		List<Notice> noticeList = currentSession
				.createQuery("from Notice where Date(curDate()) between startDate and endDate").getResultList();

		return noticeList;
	}

	@Override
	public void saveNotice(Notice theNotice) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theNotice);
	}

}
