package com.tdc.app.platform.services;

import java.util.Map;

import com.tdc.app.platform.entities.LeadStatus;

public interface LeadStatusService {

	Map<String, Object> getLeadStatuss();

	Map<String, Object> getLeadStatusById(int leadStatusId);

	Map<String, Object> createLeadStatus(LeadStatus leadStatus);

	Map<String, Object> updateLeadStatusById(int leadStatusId, LeadStatus leadStatus);

	Map<String, Object> deleteLeadStatusById(int leadStatusId);
}
