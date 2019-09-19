package com.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("UPDATE Task SET status=:status WHERE taskId=:taskId")
	@Modifying
    void updateStatus(@Param(value = "status") String status,@Param(value = "taskId") long taskId);
	
}
