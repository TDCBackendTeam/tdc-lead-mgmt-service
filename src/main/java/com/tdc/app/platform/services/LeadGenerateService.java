package com.tdc.app.platform.services;

import java.util.List;
import java.util.Map;

import com.tdc.app.platform.dtos.DeleteRequest;
import com.tdc.app.platform.dtos.LeadGenerateDto;
import com.tdc.app.platform.dtos.LeadGenerateResponse;
import com.tdc.app.platform.entities.LeadGenerate;

public interface LeadGenerateService {

	List<LeadGenerateResponse> getAllLead(String instituteCode);

	LeadGenerateResponse getLeadById(String instituteCode, int leadId);

	LeadGenerate saveLead(LeadGenerateDto leadDTO);

	void deleteLeadByIds(String instituteCode, DeleteRequest deleteRequest);

	Map<String, Object> updateLeadGenerateById(String instituteCode, int leadId, LeadGenerateDto request);

	void deleteByEmail(String email);

	void deleteFollowUpId(List<Integer> followUpId);

}
