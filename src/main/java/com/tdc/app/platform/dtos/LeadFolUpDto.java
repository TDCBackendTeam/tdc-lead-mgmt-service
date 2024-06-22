package com.tdc.app.platform.dtos;

import com.tdc.app.platform.entities.LeadGenerate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadFolUpDto {
    private LocalDate folUpDate;
    private String folUpTime;
    private String Comments;
    private LeadGenerate leadGenerate;
}
