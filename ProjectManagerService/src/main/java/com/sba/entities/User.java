package com.sba.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private long userId;
	private BigInteger employeeId;
	private String firstname;
	private String lastname;
	private long projectId;
	private long taskId;

	public User() {
		//public constructor
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	@Column(name="employee_id")
	public BigInteger getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(BigInteger employeeId) {
		this.employeeId = employeeId;
	}


	@Column(length=100)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	@Column(length=100)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	/**
	 * @return the project_id
	 */
	public long getProjectId() {
		return projectId;
	}


	/**
	 * @param project_id the project_id to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}


	/**
	 * @return the task_id
	 */
	public long getTaskId() {
		return taskId;
	}


	/**
	 * @param task_id the task_id to set
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}


	

}