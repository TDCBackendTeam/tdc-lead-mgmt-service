package com.tdc.app.platform.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadStatusResponse {
	
	private Integer statusId;
	
	private String status;
}
