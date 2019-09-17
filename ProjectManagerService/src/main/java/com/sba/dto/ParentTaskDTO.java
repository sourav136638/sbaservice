package com.sba.dto;

import java.io.Serializable;

public class ParentTaskDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4197964117684094567L;
	private long parentId;
	private String parentTask;
	/**
	 * @return the parentId
	 */
	public long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the parentTask
	 */
	public String getParentTask() {
		return parentTask;
	}
	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	

}
