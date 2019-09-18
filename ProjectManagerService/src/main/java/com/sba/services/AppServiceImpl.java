package com.sba.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sba.api.AppController;
import com.sba.dto.ParentTaskDTO;
import com.sba.dto.ProjectDTO;
import com.sba.dto.TaskDTO;
import com.sba.dto.UserDTO;
import com.sba.entities.ParentTask;
import com.sba.entities.Project;
import com.sba.entities.Task;
import com.sba.entities.User;
import com.sba.repository.ParentRepository;
import com.sba.repository.ProjectRepository;
import com.sba.repository.TaskRepository;
import com.sba.repository.UserRepository;

/**
 * @author sourav
 *
 */

@Service
public class AppServiceImpl {
	
	private static final Logger log = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	ParentRepository parentRepo;
	
	
	public AppServiceImpl(UserRepository userRepo, ProjectRepository projectRepo, TaskRepository taskRepo,
			ParentRepository parentRepo) {
		super();
		this.userRepo = userRepo;
		this.projectRepo = projectRepo;
		this.taskRepo = taskRepo;
		this.parentRepo = parentRepo;
	}



	public List<UserDTO> getUserList() {
		List<User> userList = userRepo.findAll();		
		List<UserDTO> userDtoList = new ArrayList<>();
		for (User user : userList) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDtoList.add(userDTO);
		}	
		return userDtoList;
	}
	
	@Transactional
	public void createUser(UserDTO userDto) {
		User userEntity = new User();
		BeanUtils.copyProperties(userDto, userEntity);
		userRepo.save(userEntity);
	}
	
	@Transactional
	public void deleteUserById(long id) {
		userRepo.deleteById(id);
	}
	
	public List<ProjectDTO> getProjectList() {
		List<Project> projectList = projectRepo.findAll();
		List<ProjectDTO> projDtoList = new ArrayList<>();
		for (Project project : projectList) {
			ProjectDTO projectDTO = new ProjectDTO();
			BeanUtils.copyProperties(project, projectDTO);
			projDtoList.add(projectDTO);
		}
		return projDtoList;
	}
	
	@Transactional
	public void createProject(ProjectDTO projectDto) {
		Project projectEntity = new Project();
		BeanUtils.copyProperties(projectDto, projectEntity);
		Project persistedProject = projectRepo.save(projectEntity);		
		if (!StringUtils.isEmpty(projectDto.getManagerId())) {
			userRepo.addProject(persistedProject.getProjectId(), projectDto.getManagerId());
		}
	}
	
	public List<TaskDTO> getTasksList() {
		List<Task> taskList = taskRepo.findAll();		
		List<TaskDTO> taskDtoList = new ArrayList<>();
		for (Task task : taskList) {
			TaskDTO taskDTO = new TaskDTO();
			BeanUtils.copyProperties(task, taskDTO);
			taskDtoList.add(taskDTO);
		}		
		return taskDtoList;
	}
	
	@Transactional
	public void createTask(TaskDTO taskDto) {
		Task taskEntity = new Task();
		BeanUtils.copyProperties(taskDto, taskEntity);
		taskRepo.save(taskEntity);		
	}
	public List<ParentTaskDTO> getParentTasks() {
		List<ParentTask> parentTaskList = parentRepo.findAll();		
		List<ParentTaskDTO> parentTaskDtoList = new ArrayList<>();
		for (ParentTask task : parentTaskList) {
			ParentTaskDTO taskDTO = new ParentTaskDTO();
			BeanUtils.copyProperties(task, taskDTO);
			parentTaskDtoList.add(taskDTO);
		}		
		return parentTaskDtoList;
	}
}
