package com.tdc.app.platform.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadGenerateResponse {
	
	private int leadId;

    private StudentResponse student;

    private String instituteCode;

    private Integer stageId;

    private Integer statusId;

    private Integer sourceId;

    private String allocatedTo;

    private LocalDateTime createdDate;

    private String remark;

    private List<LeadFolUpResponse> leadFolUp = new ArrayList<>();
}
