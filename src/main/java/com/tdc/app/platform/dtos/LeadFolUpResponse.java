package com.tdc.app.platform.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadFolUpResponse {

	private Integer leadFollowUpId;

	private LocalDate folUpDate;

	private String folUpTime;

	private String Comments;

}
