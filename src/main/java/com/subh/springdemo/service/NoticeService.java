package com.subh.springdemo.service;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.entity.Notice;

public interface NoticeService {

	public APIResponse getNotices();

	public APIResponse saveNotice(Notice theNotice);

}
