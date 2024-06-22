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

import com.tdc.app.platform.entities.LeadStatus;
import com.tdc.app.platform.services.LeadStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/leadStatus")
@Tag(name = "LeadStatus Generate", description = "The LeadStatus api")
public class LeadStatusController {

	@Autowired
	private LeadStatusService leadStatusService;

	@GetMapping
	@Operation(summary = "Fetch all LeadStatus", description = "fetches all LeadStatus entities and their data from data leadStatus")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> getLeadStatuss() {
		log.info(">> getDesignation({})");
		return new ResponseEntity<>(leadStatusService.getLeadStatuss(), HttpStatus.OK);
	}

	@GetMapping("/{leadStatusId}")
	@Operation(summary = "Fetch LeadStatus by id", description = "fetches LeadStatus entities and their data from data leadStatus by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> getDesignationById(@PathVariable int leadStatusId) {
		log.info(">> getDesignation({})", leadStatusId);
		return new ResponseEntity<>(leadStatusService.getLeadStatusById(leadStatusId), HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Save LeadStatus Records", description = " Save LeadStatus record in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> createLeadStatus(@Valid @RequestBody LeadStatus leadStatus) {
		log.info("create Designation entered and incoming request {}", leadStatus);
		return new ResponseEntity<>(leadStatusService.createLeadStatus(leadStatus), HttpStatus.CREATED);
	}

	@PutMapping("/{leadStatusId}")
	@Operation(summary = "Update LeadStatus by id", description = "Update LeadStatus entities and their data from data leadStatus by leadStatusId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> updateLeadStatusById(@PathVariable int leadStatusId,
			@RequestBody LeadStatus leadStatus) {
		log.info(">> updateLeadStatusById({})", leadStatusId, leadStatus);
		return new ResponseEntity<>(leadStatusService.updateLeadStatusById(leadStatusId, leadStatus),
				HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{leadStatusId}")
	@Operation(summary = "Delete LeadStatus by id", description = "Delete LeadStatus entities and their data from data leadStatus by leadStatusId ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<Map<String, Object>> deleteDesignationById(@PathVariable int leadStatusId) {
		log.info(">> deleteDesignationById({})", leadStatusId);
		return new ResponseEntity<>(leadStatusService.deleteLeadStatusById(leadStatusId), HttpStatus.NO_CONTENT);
	}

}
