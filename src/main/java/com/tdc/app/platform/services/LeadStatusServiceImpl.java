package com.tdc.app.platform.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.app.platform.entities.LeadStatus;
import com.tdc.app.platform.repositories.LeadStatusRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeadStatusServiceImpl implements LeadStatusService {

	@Autowired
	private LeadStatusRepository leadStatusRepository;

	@Override
	public Map<String, Object> getLeadStatuss() {
		log.info(">> getLeadStatuss({})");
		return Map.of("response", leadStatusRepository.findAll());
	}

	@Override
	public Map<String, Object> getLeadStatusById(int leadStatusId) {
		log.info(">> getLeadStatusById({})", leadStatusId);
		return Map.of("response", leadStatusRepository.findById(leadStatusId).orElse(null));
	}

	@Override
	public Map<String, Object> createLeadStatus(LeadStatus leadStatus) {
		log.info(">> createLeadStatus({})", leadStatus);
		return Map.of("response", leadStatusRepository.save(leadStatus));
	}

	@Override
	@Transactional
	public Map<String, Object> updateLeadStatusById(int leadStatusId, LeadStatus leadStatus) {
		log.info(">> updateLeadStatusById({}, {})", leadStatusId, leadStatus);
		LeadStatus existLeadStatus = leadStatusRepository.findById(leadStatusId).orElse(null);
		existLeadStatus.setStatus(leadStatus.getStatus());
		return Map.of("response", existLeadStatus);
	}

	@Override
	public Map<String, Object> deleteLeadStatusById(int leadStatusId) {
		log.info(">> deleteLeadStatusById({})", leadStatusId);
		LeadStatus existLeadStatus = leadStatusRepository.findById(leadStatusId).orElse(null);
		leadStatusRepository.delete(existLeadStatus);
		return Map.of("message", "Deleted!!");
	}

}
