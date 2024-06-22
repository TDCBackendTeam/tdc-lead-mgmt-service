package com.tdc.app.platform.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeadGenerateRequest {

	private String studentName;

	private String emailId;

	private String phoneNo;

	private String altPhoneNo;

	private String qualification;

	private String stageCode;

	private String status;

	private String source;
	
	private String executive;

	private LocalDate followupDate;

	private String followupTime;

	private String fatherName;

	private String motherName;

	private String course;

}
