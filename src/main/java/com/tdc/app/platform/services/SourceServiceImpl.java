package com.tdc.app.platform.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.app.platform.entities.Source;
import com.tdc.app.platform.repositories.SourceRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SourceServiceImpl implements SourceService {

	@Autowired
	private SourceRepository sourceRepository;

	@Override
	public Map<String, Object> getSources() {
		log.info(">> getSources({})");
		return Map.of("response", sourceRepository.findAll());
	}

	@Override
	public Map<String, Object> getSourceById(int sourceId) {
		log.info(">> getSourceById({})", sourceId);
		return Map.of("response", sourceRepository.findById(sourceId).orElse(null));
	}

	@Override
	public Map<String, Object> createSource(Source source) {
		log.info(">> createSource({})", source);
		return Map.of("response", sourceRepository.save(source));
	}

	@Override
	@Transactional
	public Map<String, Object> updateSourceById(int sourceId, Source source) {
		log.info(">> updateSourceById({}, {})", sourceId, source);
		Source existSource = sourceRepository.findById(sourceId).orElse(null);
		existSource.setSourceName(source.getSourceName());
		return Map.of("response", existSource);
	}

	@Override
	public Map<String, Object> deleteSourceById(int sourceId) {
		log.info(">> deleteSourceById({})", sourceId);
		Source existSource = sourceRepository.findById(sourceId).orElse(null);
		sourceRepository.delete(existSource);
		return Map.of("message", "Deleted!!");
	}

}
