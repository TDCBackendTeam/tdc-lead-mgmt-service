package com.tdc.app.platform.services;

import java.util.Map;

import com.tdc.app.platform.entities.Stage;

public interface StageService {

	Map<String, Object> getStages();

	Map<String, Object> getStageById(int stageId);

	Map<String, Object> createStage(Stage stage);

	Map<String, Object> updateStageById(int stageId, Stage stage);

	Map<String, Object> deleteStageById(int stageId);
}
