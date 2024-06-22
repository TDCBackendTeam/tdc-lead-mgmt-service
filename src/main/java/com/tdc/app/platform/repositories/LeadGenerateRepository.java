package com.tdc.app.platform.repositories;

import java.util.List;
import java.util.Optional;

import com.tdc.app.platform.entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdc.app.platform.entities.LeadGenerate;

@Repository
public interface LeadGenerateRepository extends JpaRepository<LeadGenerate, Integer> {

	List<LeadGenerate> findAllByInstituteCode(String instituteCode);

	Optional<LeadGenerate> findByLeadIdAndInstituteCode(int leadId, String instituteCode);

	List<LeadGenerate> findByLeadIdInAndInstituteCode(List<Integer> leadIds, String instituteCode);

	LeadGenerate findByStudent(Student studentId);

}
