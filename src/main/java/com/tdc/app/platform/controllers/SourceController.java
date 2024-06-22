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

import com.tdc.app.platform.entities.Source;
import com.tdc.app.platform.services.SourceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/source")
@Tag(name = "Source Generate", description = "The Source api")
public class SourceController {

	@Autowired
	private SourceService sourceService;

	@GetMapping
	@Operation(summary = "Fetch all Source", description = "fetches all Source entities and their data from data source")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> getSources() {
		log.info(">> getDesignation({})");
		return new ResponseEntity<>(sourceService.getSources(), HttpStatus.OK);
	}

	@GetMapping("/{sourceId}")
	@Operation(summary = "Fetch Source by id", description = "fetches Source entities and their data from data source by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> getDesignationById(@PathVariable int sourceId) {
		log.info(">> getDesignation({})", sourceId);
		return new ResponseEntity<>(sourceService.getSourceById(sourceId), HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Save Source Records", description = " Save Source record in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> createSource(@Valid @RequestBody Source source) {
		log.info("create Designation entered and incoming request {}", source);
		return new ResponseEntity<>(sourceService.createSource(source), HttpStatus.CREATED);
	}

	@PutMapping("/{sourceId}")
	@Operation(summary = "Update Source by id", description = "Update Source entities and their data from data source by sourceId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> updateSourceById(@PathVariable int sourceId,
			@RequestBody Source source) {
		log.info(">> updateSourceById({})", sourceId, source);
		return new ResponseEntity<>(sourceService.updateSourceById(sourceId, source), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{sourceId}")
	@Operation(summary = "Delete Designation by id", description = "Delete Designation entities and their data from data source by instituteCodeId and DesignationId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> deleteDesignationById(@PathVariable int sourceId) {
		log.info(">> deleteDesignationById({})", sourceId);
		return new ResponseEntity<>(sourceService.deleteSourceById(sourceId), HttpStatus.NO_CONTENT);
	}

}
