package com.sba.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the parent_task database table.
 * 
 */
@Entity
@Table(name="parent_task")
@NamedQuery(name="ParentTask.findAll", query="SELECT p FROM ParentTask p")
public class ParentTask implements Serializable {
	private static final long serialVersionUID = 1L;
	private long parentId;
	private String parentTask;
	/* private List<Task> tasks; */

	public ParentTask() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_id", unique=true, nullable=false)
	public long getParentId() {
		return this.parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


	@Column(name="parent_task", length=100)
	public String getParentTask() {
		return this.parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}


	/*
	 * //bi-directional many-to-one association to Task
	 * 
	 * @OneToMany(mappedBy="parentTask") public List<Task> getTasks() { return
	 * this.tasks; }
	 * 
	 * public void setTasks(List<Task> tasks) { this.tasks = tasks; }
	 * 
	 */

}