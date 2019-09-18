package com.sba.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;


@JsonView
public class ProjectDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1113282337154615257L;
	private long projectId;
	private String project;
	@JsonFormat(pattern="dd-MMM-yyyy")
	private Date startdate;
	@JsonFormat(pattern="dd-MMM-yyyy")
	private Date enddate;
	private int priority;
	private long managerId;//user_id
	private int completed;
	/**
	 * @return the projectId
	 */
	public long getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}
	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}
	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the managerId
	 */
	public long getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return the completed
	 */
	public int getCompleted() {
		return completed;
	}
	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectDTO [projectId=" + projectId + ", project=" + project + ", startdate=" + startdate + ", enddate="
				+ enddate + ", priority=" + priority + ", managerId=" + managerId + ", completed=" + completed + "]";
	}
	
	
	
}
