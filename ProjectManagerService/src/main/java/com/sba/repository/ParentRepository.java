package com.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.entities.ParentTask;

public interface ParentRepository extends JpaRepository<ParentTask, Long> {

}
