package com.tdc.app.platform.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdc.app.platform.services.LeadGenerateService;
import com.tdc.app.platform.dtos.DeleteRequest;
import com.tdc.app.platform.dtos.LeadGenerateDto;
import com.tdc.app.platform.dtos.LeadGenerateResponse;
import com.tdc.app.platform.entities.LeadGenerate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/lead")
@Tag(name = "Lead Generate", description = "The Lead api")
public class LeadGenerateController {

	@Autowired
	private LeadGenerateService leadService;

	@GetMapping
	@Operation(summary = "Fetch All Lead by Institute Code ", description = "fetches all Lead entities and their data from data source by instituteCodeId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<List<LeadGenerateResponse>> getStaff(@RequestParam String instituteCode) {
		log.info(">> getStaff({})", instituteCode);
		return new ResponseEntity<>(leadService.getAllLead(instituteCode), HttpStatus.OK);
	}

	@GetMapping("/{leadId}")
	@Operation(summary = "Fetch Lead by id", description = "fetches Lead entities and their data from data source by instituteCodeId and LeadId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	public ResponseEntity<LeadGenerateResponse> getLeadById(@RequestParam String instituteCode,
			@PathVariable int leadId) {
		log.info(">> getLeadById({})", instituteCode, leadId);
		return new ResponseEntity<>(leadService.getLeadById(instituteCode, leadId), HttpStatus.OK);
	}

	@Operation(summary = "Create Lead entities API ", description = "REST API to create new Lead entities TDC")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error") })
	@PostMapping
	public ResponseEntity<LeadGenerate> addTheLeadGenerate(@RequestBody LeadGenerateDto leadGenerateDto) {
		log.info("InstituteCode", leadGenerateDto.getInstituteCode());
		return new ResponseEntity<>(leadService.saveLead(leadGenerateDto), HttpStatus.CREATED);
	}

	@PutMapping("/{leadId}")
	@Operation(summary = "Update Lead entities API ", description = "REST API to create new Lead entities TDC")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error") })
	public ResponseEntity<Map<String, Object>> updateLeadGenerateById(@RequestHeader String instituteCode,
			@PathVariable int leadId, @RequestBody LeadGenerateDto leadGenerateDto) {
		log.info(">> updateLeadGenerateById({})", leadId, leadGenerateDto);
		return new ResponseEntity<>(leadService.updateLeadGenerateById(instituteCode, leadId, leadGenerateDto),
				HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Delete Lead by IDS", description = "Deletes a Lead entity from the data source by instituteCodeId and leadIds")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Lead not found") })
	@DeleteMapping
	public ResponseEntity<Void> deleteLeadByIds(@RequestHeader String instituteCode,
			@RequestBody DeleteRequest deleteRequest) {
		log.info(">> deleteLeadByIds({}, {})", instituteCode, deleteRequest);
		leadService.deleteLeadByIds(instituteCode, deleteRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Operation(summary = "Delete Lead by email", description = "Deletes a Lead entity from the data source by email")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Lead not found") })
	@DeleteMapping("/email")
	public ResponseEntity<LeadGenerate> deleteById(@RequestParam String email) {
		log.info(">> deleteLeadByIds({})", email);
		leadService.deleteByEmail(email);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Delete Lead by Follow up IDS", description = "Deletes a Followup entity from the data source by Followup leadIds")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Lead not found") })
	@DeleteMapping("leadfollowup")
	public ResponseEntity<Void> deleteLeadByFollowUpID(@RequestBody List<Integer> followUpId) {
		leadService.deleteFollowUpId(followUpId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
