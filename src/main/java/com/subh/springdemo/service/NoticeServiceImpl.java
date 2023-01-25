package com.subh.springdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dao.NoticeDAO;
import com.subh.springdemo.dto.NoticeEntityResponse;
import com.subh.springdemo.entity.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	@Transactional
	public APIResponse getNotices() {
		APIResponse apiResponse = new APIResponse();
		List<Notice> noticeList = noticeDAO.getNotices();
		List<NoticeEntityResponse> noticeEntityResponseList = buildNoticeEntityResponse(noticeList);
		apiResponse.setData(noticeEntityResponseList);
		return apiResponse;
	}

	private List<NoticeEntityResponse> buildNoticeEntityResponse(List<Notice> noticeList) {

		List<NoticeEntityResponse> noticeEntityResponseList = new ArrayList<>();
		NoticeEntityResponse noticeEntityResponse = null;
		for (Notice notice : noticeList) {
			noticeEntityResponse = new NoticeEntityResponse();
			noticeEntityResponse.setNoticeDetails(notice.getNoticeDetails());
			noticeEntityResponseList.add(noticeEntityResponse);
		}
		return noticeEntityResponseList;
	}

	@Override
	@Transactional
	public APIResponse saveNotice(Notice theNotice) {
		APIResponse apiResponse = new APIResponse();
		noticeDAO.saveNotice(theNotice);
		apiResponse.setData("Notice saved successfully");
		return apiResponse;
	}

}
