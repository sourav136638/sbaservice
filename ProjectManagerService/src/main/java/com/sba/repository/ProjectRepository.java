package com.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	/*
	 * @Query("select p , (select count(t1) from sba.Task t1 where t.projectId=p.projectId) as \n"
	 * +
	 * "noOfTask ,(select count(t2) from sba.Task t2 where t.projectId=p.projectId and status=:status) as \n"
	 * + "completed from sba.Project p where projectId =:projectId") void
	 * getProjectList(@Param(value="projectId") long
	 * projectId,@Param(value="status") String status);
	 */
}
