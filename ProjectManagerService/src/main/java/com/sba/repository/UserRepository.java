/**
 * 
 */
package com.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.entities.User;

/**
 * @author sourav
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("UPDATE User SET projectId=:projectId WHERE userId=:userId")
	@Modifying
    void addProject(@Param(value = "projectId") long projectId,@Param(value = "userId") long userId);
		
	
	
	
	

}
