package com.tdc.app.platform.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.tdc.app.platform.dtos.LeadGenerateDto;
import com.tdc.app.platform.entities.LeadFolUp;
import com.tdc.app.platform.entities.LeadGenerate;
import com.tdc.app.platform.entities.LeadStatus;
import com.tdc.app.platform.entities.Source;
import com.tdc.app.platform.entities.Stage;
import com.tdc.app.platform.entities.Student;
import com.tdc.app.platform.entities.StudentAddress;
import com.tdc.app.platform.entities.StudentCourse;

public class LeadGenerateMapper {

	public static LeadGenerate toEntity(LeadGenerateDto dto) {
		LeadGenerate lead = new LeadGenerate();
		lead.setInstituteCode(dto.getInstituteCode());
		lead.setAllocatedTo(dto.getAllocatedTo());
		lead.setCreatedDate(LocalDate.now().atStartOfDay());
		lead.setRemark(dto.getRemark());

		Student student = new Student();
		student.setStudentName(dto.getStudent().getStudentName());
		student.setEmailId(dto.getStudent().getEmailId());
		student.setPhoneNo(dto.getStudent().getPhoneNo());
		student.setAltPhoneNo(dto.getStudent().getAltPhoneNo());
		student.setBirthday(dto.getStudent().getBirthday());
		student.setFatherName(dto.getStudent().getFatherName());
		student.setMotherName(dto.getStudent().getMotherName());

		StudentCourse course = new StudentCourse();
		course.setName(dto.getStudent().getCourse());
		student.setCourse(course);

		StudentAddress studentAddress = new StudentAddress();
		studentAddress.setStreet1(dto.getStudent().getAddress().getStreet1());
		studentAddress.setStreet2(dto.getStudent().getAddress().getStreet2());
		studentAddress.setState(dto.getStudent().getAddress().getState());
		studentAddress.setCity(dto.getStudent().getAddress().getCity());
		studentAddress.setCountry(dto.getStudent().getAddress().getCountry());
		studentAddress.setPincode(dto.getStudent().getAddress().getPincode());
		student.setAddress(studentAddress);
		lead.setStudent(student);

		Stage stage = new Stage();
		stage.setStageName(dto.getStage());
		lead.setStage(stage);

		LeadStatus status = new LeadStatus();
		status.setStatus(dto.getStatus());
		lead.setStatus(status);

		Source source = new Source();
		source.setSourceName(dto.getSource());
		lead.setSource(source);

		List<LeadFolUp> leadFolUpList = dto.getLeadFolUp().stream().map(followUpDTO -> {
			LeadFolUp followUp = new LeadFolUp();
			followUp.setFolUpDate(followUpDTO.getFolUpDate());
			followUp.setFolUpTime(followUpDTO.getFolUpTime());
			followUp.setComments(followUpDTO.getComments());
			followUp.setLeadGenerate(lead);
			return followUp;
		}).collect(Collectors.toList());
		lead.setLeadFolUp(leadFolUpList);
		return lead;
	}
}
