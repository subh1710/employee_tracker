package com.subh.springdemo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leave_master")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "type")
	private String type;

	@Column(name = "details")
	private String details;

	@Column(name = "approval_status")
	private String approvalStatus;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "employee_master_id")
	private Employee employeeLeave;

	public Leave() {
	}

	public Leave(Date startDate, Date endDate, String type, String details, String approvalStatus) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.details = details;
		this.approvalStatus = approvalStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Employee getEmployeeLeave() {
		return employeeLeave;
	}

	public void setEmployeeLeave(Employee employeeLeave) {
		this.employeeLeave = employeeLeave;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", type=" + type + ", details="
				+ details + ", approvalStatus=" + approvalStatus + "]";
	}

}
