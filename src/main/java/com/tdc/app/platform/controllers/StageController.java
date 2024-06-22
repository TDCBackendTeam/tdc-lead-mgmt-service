package com.tdc.app.platform.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdc.app.platform.entities.Stage;
import com.tdc.app.platform.services.StageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/stage")
@Tag(name = "Stage Generate", description = "The Stage api")
public class StageController {

	@Autowired
	private StageService stageService;

	@GetMapping
	@Operation(summary = "Fetch all Stage", description = "fetches all Stage entities and their data from data stage")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> getStages() {
		log.info(">> getDesignation({})");
		return new ResponseEntity<>(stageService.getStages(), HttpStatus.OK);
	}

	@GetMapping("/{stageId}")
	@Operation(summary = "Fetch Stage by id", description = "fetches Stage entities and their data from data stage by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> getDesignationById(@PathVariable int stageId) {
		log.info(">> getDesignation({})", stageId);
		return new ResponseEntity<>(stageService.getStageById(stageId), HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Save Stage Records", description = " Save Stage record in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> createStage(@Valid @RequestBody Stage stage) {
		log.info("create Designation entered and incoming request {}", stage);
		return new ResponseEntity<>(stageService.createStage(stage), HttpStatus.CREATED);
	}

	@PutMapping("/{stageId}")
	@Operation(summary = "Update Stage by id", description = "Update Stage entities and their data from data stage by stageId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> updateStageById(@PathVariable int stageId,
			@RequestBody Stage stage) {
		log.info(">> updateStageById({})", stageId, stage);
		return new ResponseEntity<>(stageService.updateStageById(stageId, stage), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{stageId}")
	@Operation(summary = "Delete Designation by id", description = "Delete Designation entities and their data from data stage by instituteCodeId and DesignationId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> deleteDesignationById(@PathVariable int stageId) {
		log.info(">> deleteDesignationById({})", stageId);
		return new ResponseEntity<>(stageService.deleteStageById(stageId), HttpStatus.NO_CONTENT);
	}

}
