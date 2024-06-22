package com.tdc.app.platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdc.app.platform.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
