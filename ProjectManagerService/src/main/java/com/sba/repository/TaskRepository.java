package com.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
