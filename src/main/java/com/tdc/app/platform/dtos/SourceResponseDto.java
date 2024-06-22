package com.tdc.app.platform.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceResponseDto {

	private Integer sourceId;

	private String sourceName;
}
