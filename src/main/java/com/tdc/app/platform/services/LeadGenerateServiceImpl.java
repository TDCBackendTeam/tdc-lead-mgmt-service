package com.tdc.app.platform.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdc.app.platform.Mapper.LeadGenerateMapper;
import com.tdc.app.platform.dtos.DeleteRequest;
import com.tdc.app.platform.dtos.LeadGenerateDto;
import com.tdc.app.platform.dtos.LeadGenerateResponse;
import com.tdc.app.platform.entities.LeadFolUp;
import com.tdc.app.platform.entities.LeadGenerate;
import com.tdc.app.platform.entities.LeadStatus;
import com.tdc.app.platform.entities.Source;
import com.tdc.app.platform.entities.Stage;
import com.tdc.app.platform.entities.Student;
import com.tdc.app.platform.entities.StudentAddress;
import com.tdc.app.platform.entities.StudentCourse;
import com.tdc.app.platform.exceptions.ResourceNotFoundException;
import com.tdc.app.platform.repositories.AddressRepository;
import com.tdc.app.platform.repositories.LeadFolUpRepository;
import com.tdc.app.platform.repositories.LeadGenerateRepository;
import com.tdc.app.platform.repositories.LeadStatusRepository;
import com.tdc.app.platform.repositories.SourceRepository;
import com.tdc.app.platform.repositories.StageRepository;
import com.tdc.app.platform.repositories.StudentCourseRepository;
import com.tdc.app.platform.repositories.StudentRepository;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;

@Slf4j
@Service
public class LeadGenerateServiceImpl implements LeadGenerateService {

	@Autowired
	private LeadGenerateRepository leadGenerateRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SourceRepository sourceRepository;

	@Autowired
	private LeadFolUpRepository folUpRepository;

	@Autowired
	private LeadStatusRepository leadStatusRepository;

	@Autowired
	private StageRepository stageRepository;

	@Autowired
	private StudentCourseRepository courseRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	@Override
	public List<LeadGenerateResponse> getAllLead(String instituteCode) {
		log.info(">> getAllLead({})", instituteCode);
		List<LeadGenerate> leadGenerates = leadGenerateRepository.findAllByInstituteCode(instituteCode);
		return leadGenerates.stream().map(lead -> {
			LeadGenerateResponse res = mapper.map(lead, LeadGenerateResponse.class);
			res.getStudent().setCourseId(lead.getStudent().getCourse().getStuTypeId());
			res.setStageId(lead.getStage().getStageId());
			res.setStatusId(lead.getStatus().getStatusId());
			res.setSourceId(lead.getSource().getSourceId());
			return res;
		}).collect(Collectors.toList());
	}

	@Transactional
	public LeadGenerateResponse getLeadById(String instituteCode, int leadId) {
		log.info(">> getgetLeadByIdById({}, {})", instituteCode, leadId);
		LeadGenerate lead = leadGenerateRepository.findByLeadIdAndInstituteCode(leadId, instituteCode)
				.orElseThrow(() -> new RuntimeException("Lead Not Found By Id"));
		LeadGenerateResponse res = mapper.map(lead, LeadGenerateResponse.class);
		res.getStudent().setCourseId(lead.getStudent().getCourse().getStuTypeId());
		res.setStageId(lead.getStage().getStageId());
		res.setStatusId(lead.getStatus().getStatusId());
		res.setSourceId(lead.getSource().getSourceId());
		return res;
	}

	@Transactional
	@Override
	public LeadGenerate saveLead(LeadGenerateDto leadDTO) {
		try {
			LeadGenerate lead = LeadGenerateMapper.toEntity(leadDTO);

			// Save the student entity if it is new
			Student student = lead.getStudent();
			log.info("Student :", student.getStudentName());
			if (student != null) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse = student.getCourse();
				log.info("Course", studentCourse);
				if (studentCourse != null) {
					studentCourse = courseRepository.save(studentCourse);
					student.setCourse(studentCourse);
				}
				StudentAddress studentAddress = new StudentAddress();
				studentAddress = student.getAddress();
				if (studentAddress != null) {
					studentAddress = addressRepository.save(studentAddress);
					student.setAddress(studentAddress);
				}
				student = studentRepository.save(student);
				lead.setStudent(student);
			}
			// Save the stage entity
			Stage stage = new Stage();
			stage = lead.getStage();
			if (stage != null) {
				stage = stageRepository.save(stage);
				lead.setStage(stage);
			}

			// Save the status Entity
			LeadStatus leadStatus = new LeadStatus();
			leadStatus = lead.getStatus();
			if (leadStatus != null) {
				leadStatus = leadStatusRepository.save(leadStatus);
				lead.setStatus(leadStatus);
			}

			// Save the Source entity
			Source source = new Source();
			source = lead.getSource();
			if (source != null) {
				source = sourceRepository.save(source);
				lead.setSource(source);
			}
			// Save the lead entity
			lead = leadGenerateRepository.save(lead);

			// Save the lead follow-ups
			List<LeadFolUp> leadFolUpList = lead.getLeadFolUp();
			for (LeadFolUp followUp : leadFolUpList) {
				followUp.setLeadGenerate(lead);
				folUpRepository.save(followUp);
			}
			return lead;
		} catch (javax.validation.ConstraintViolationException e) {
			throw new ConstraintViolationException(e.getConstraintViolations());
		}
	}

	@Transactional
	@Override
	public void deleteLeadByIds(String instituteCode, DeleteRequest deleteRequest) {
		log.info("Attempting to delete leads with ID {} for institute code {}", deleteRequest, instituteCode);
		List<LeadGenerate> leads = leadGenerateRepository.findByLeadIdInAndInstituteCode(deleteRequest.getIds(),
				instituteCode);
		if (!leads.isEmpty()) {
			leads.stream().forEach(lead -> {
				leadGenerateRepository.delete(lead);
			});
		}
	}

	@Transactional
	@Override
	public Map<String, Object> updateLeadGenerateById(String instituteCode, int leadId, LeadGenerateDto request) {
		log.info(">> updateLeadGenerateById({})", instituteCode, leadId, request);
		LeadGenerate exist = leadGenerateRepository.findByLeadIdAndInstituteCode(leadId, instituteCode)
				.orElseThrow(() -> new ResourceNotFoundException("Lead Not Found By : " + leadId));
		exist.setRemark(request.getRemark());
		if (request.getStudent() != null) {
			exist.getStudent().setStudentName(request.getStudent().getStudentName());
			exist.getStudent().setEmailId(request.getStudent().getEmailId());
			exist.getStudent().setPhoneNo(request.getStudent().getPhoneNo());
			exist.getStudent().setAltPhoneNo(request.getStudent().getAltPhoneNo());
			exist.getStudent().setBirthday(request.getStudent().getBirthday());
			exist.getStudent().setFatherName(request.getStudent().getFatherName());
			exist.getStudent().setMotherName(request.getStudent().getMotherName());
		}
		return Map.of("response", exist);

	}

	@Transactional
	@Override
	public void deleteByEmail(String email) {
		log.info("before enter into the service class");
		Student student = studentRepository.findByEmailId(email);
		if (student != null) {
			LeadGenerate leadGenerate = leadGenerateRepository.findByStudent(student);
			int leadId = leadGenerate.getLeadId();
			int studentId = leadGenerate.getStudent().getStudentId();
			int source_Id = leadGenerate.getSource().getSourceId();
			int stage_Id = leadGenerate.getStage().getStageId();
			int course_Id = student.getCourse().getStuTypeId();
			int address_Id = student.getAddress().getAddId();
			try {
				leadGenerateRepository.deleteById(leadId);
				studentRepository.deleteById(studentId);
				addressRepository.deleteById(address_Id);
				sourceRepository.deleteById(source_Id);
				stageRepository.deleteById(stage_Id);
				courseRepository.deleteById(course_Id);
			} catch (Exception e) {
				log.info("Something wrong");
			}
		}

	}

	@Override
	public void deleteFollowUpId(List<Integer> followUpIds) {
		for (int followUpId : followUpIds) {
			folUpRepository.deleteById(followUpId);
		}

	}

}
