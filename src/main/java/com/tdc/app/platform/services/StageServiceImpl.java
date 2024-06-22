package com.tdc.app.platform.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.app.platform.entities.Stage;
import com.tdc.app.platform.repositories.StageRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StageServiceImpl implements StageService {

	@Autowired
	private StageRepository stageRepository;

	@Override
	public Map<String, Object> getStages() {
		log.info(">> getStages({})");
		return Map.of("response", stageRepository.findAll());
	}

	@Override
	public Map<String, Object> getStageById(int stageId) {
		log.info(">> getStageById({})", stageId);
		return Map.of("response", stageRepository.findById(stageId).orElse(null));
	}

	@Override
	public Map<String, Object> createStage(Stage stage) {
		log.info(">> createStage({})", stage);
		return Map.of("response", stageRepository.save(stage));
	}

	@Override
	@Transactional
	public Map<String, Object> updateStageById(int stageId, Stage stage) {
		log.info(">> updateStageById({}, {})", stageId, stage);
		Stage existStage = stageRepository.findById(stageId).orElse(null);
		existStage.setStageName(stage.getStageName());
		return Map.of("response", existStage);
	}

	@Override
	public Map<String, Object> deleteStageById(int stageId) {
		log.info(">> deleteStageById({})", stageId);
		Stage existStage = stageRepository.findById(stageId).orElse(null);
		stageRepository.delete(existStage);
		return Map.of("message", "Deleted!!");
	}

}
