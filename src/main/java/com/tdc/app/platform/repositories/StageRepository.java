package com.tdc.app.platform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdc.app.platform.entities.Stage;

@Repository
public interface StageRepository extends JpaRepository<Stage, Integer> {

	Optional<Stage> findByStageName(String stageCode);

}
