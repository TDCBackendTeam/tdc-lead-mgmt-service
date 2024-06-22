package com.tdc.app.platform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdc.app.platform.entities.LeadStatus;

@Repository
public interface LeadStatusRepository extends JpaRepository<LeadStatus, Integer> {

	Optional<LeadStatus> findByStatus(String status);

}
