package com.tdc.app.platform.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdc.app.platform.entities.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {

	Optional<Source> findBySourceName(String source);

}
