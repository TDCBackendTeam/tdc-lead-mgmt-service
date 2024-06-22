package com.tdc.app.platform.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadGenerateDto {

	private StudentDto student;

	@NotBlank(message = "instituteCode must not be blank")
	@Size(min = 3, message = "instituteCode must be at least 3 characters long")
	private String instituteCode;

	@NotNull(message = "stage must not be blank")
	@Size(min = 3, message = "stage must be at least 3 characters long")
	private String stage;

	@NotNull(message = "status must not be blank")
	@Size(min = 3, message = "status must be at least 3 characters long")
	private String status;

	@NotNull(message = "source must not be blank")
	@Size(min = 3, message = "source must be at least 3 characters long")
	private String source;

	@NotNull(message = "allocatedTo must not be blank")
	@Size(min = 3, message = "allocatedTo must be at least 3 characters long")
	private String allocatedTo;

	private LocalDateTime createdDate;

	@NotBlank(message = "remark must not be blank")
	@Size(min = 3, message = "remark must be at least 3 characters long")
	private String remark;

	private List<LeadFolUpDto> leadFolUp = new ArrayList<>();
}
