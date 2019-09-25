package com.sba.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@Table(name="task")
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long taskId;
	private Date endDate;
	private int priority;
	private Date startDate;
	private String status;
	private String task;
	private Long projectId;
	private Long parentId;

	
	public Task() {
		//Constructor
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id", unique=true, nullable=false)
	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	@Column(length=10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Column(length=100)
	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}


	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}


	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}


	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}