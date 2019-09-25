package com.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	
}
