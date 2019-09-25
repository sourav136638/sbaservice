package com.sba.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sba.api.AppController;
import com.sba.api.util.AppConstant;
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
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<ProjectDTO> getProjectList() {
		List<Project> projectList = projectRepo.findAll();
		List<ProjectDTO> projDtoList = new ArrayList<>();
		for (Project project : projectList) {
			ProjectDTO projectDTO = new ProjectDTO();
			BeanUtils.copyProperties(project, projectDTO);
			projectDTO.setNoOfTask(taskRepo.getCountOfTaskForProject(projectDTO.getProjectId()));
			projectDTO.setCompleted(taskRepo.getCountOfCompletedTaskForProject(projectDTO.getProjectId()));
			projDtoList.add(projectDTO);
		}
		return projDtoList;
	}

	@Transactional
	public void createProject(ProjectDTO projectDto) {
		Project projectEntity = new Project();
		BeanUtils.copyProperties(projectDto, projectEntity);
		Project persistedProject = projectRepo.save(projectEntity);
		if (!StringUtils.isEmpty(projectDto.getManagerId())&& projectDto.getManagerId()>0) {
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
		if (taskDto != null) {
			if(!taskDto.isParent()){			
				if (StringUtils.isEmpty(taskDto.getTaskId())) {
					taskEntity.setStatus(AppConstant.STATUS_OPEN);
				}
				Task persistedTask = taskRepo.save(taskEntity);
	
				if (!StringUtils.isEmpty(taskDto.getUserId())) {
					userRepo.addTask(persistedTask.getTaskId(), taskDto.getUserId());
				}
			}else {
				ParentTask parentEntity = new ParentTask();
				parentEntity.setParentTask(taskDto.getTask());
				parentRepo.save(parentEntity);
			}
		}

	}

	@Transactional
	public void completeTask(long id) {		
		log.info("completeTask");
		taskRepo.updateStatus(AppConstant.STATUS_COMPLETED,id);
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
