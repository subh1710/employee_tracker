package com.subh.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.springdemo.common.APIResponse;
import com.subh.springdemo.dto.NoticeRequestDTO;
import com.subh.springdemo.entity.Employee;
import com.subh.springdemo.entity.Notice;
import com.subh.springdemo.service.EmployeeService;
import com.subh.springdemo.service.NoticeService;

@RestController
@RequestMapping("/api")
public class NoticeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private NoticeService noticeService;

	@GetMapping("/notices")
	public ResponseEntity<APIResponse> getNotices() {
		APIResponse apiResponse = noticeService.getNotices();
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/notices")
	public ResponseEntity<APIResponse> addNotice(@RequestBody NoticeRequestDTO noticeRequestDTO) {
		Employee tmpEmployee = employeeService.getEmployee(noticeRequestDTO.getEmpId());
		if (tmpEmployee == null) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + noticeRequestDTO.getEmpId());
		}
		Notice theNotice = new Notice(noticeRequestDTO.getNoticeDetails(), noticeRequestDTO.getStartDate(),
				noticeRequestDTO.getEndDate());
		tmpEmployee.addNotice(theNotice);
		APIResponse apiResponse = noticeService.saveNotice(theNotice);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
