package com.sba.api;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sba.dto.ProjectDTO;
import com.sba.dto.TaskDTO;
import com.sba.dto.UserDTO;
import com.sba.entities.Project;
import com.sba.entities.Task;
import com.sba.entities.User;
import com.sba.repository.ProjectRepository;
import com.sba.repository.TaskRepository;
import com.sba.repository.UserRepository;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AppController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	TaskRepository taskRepo;

	/**
	 * @return the userRepo
	 */
	public UserRepository getUserRepo() {
		return userRepo;
	}

	/**
	 * @param userRepo the userRepo to set
	 */
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	/**
	 * @return the projectRepo
	 */
	public ProjectRepository getProjectRepo() {
		return projectRepo;
	}

	/**
	 * @param projectRepo the projectRepo to set
	 */
	public void setProjectRepo(ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
	}

	@GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getUsers() {
		List<User> userList = userRepo.findAll();
		System.err.println("USer list Size" + userList.size());
		List<UserDTO> userDtoList = new ArrayList<UserDTO>();
		for (User user : userList) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			userDtoList.add(userDTO);
		}
		System.err.println("DTO list Size" + userDtoList.size());
		return userDtoList;
	}

	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody UserDTO userDto) {
		User userEntity = new User();
		BeanUtils.copyProperties(userDto, userEntity);
		userRepo.save(userEntity);
	}

	@DeleteMapping(value = "/user/{id}")
	public void deleteUserById(@PathVariable long id) {
		userRepo.deleteById(id);
	}
	
	@GetMapping(path = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDTO> getProjects() {
		List<Project> projectList = projectRepo.findAll();
		System.err.println("Project list Size" + projectList.size());
		List<ProjectDTO> projDtoList = new ArrayList<ProjectDTO>();
		for (Project project : projectList) {
			ProjectDTO projectDTO = new ProjectDTO();
			BeanUtils.copyProperties(project, projectDTO);
			projDtoList.add(projectDTO);
		}
		System.err.println("DTO list Size" + projDtoList.size());
		return projDtoList;
	}

	@PostMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void createProject(@RequestBody ProjectDTO projectDto) {
		Project projectEntity = new Project();
		BeanUtils.copyProperties(projectDto, projectEntity);
		Project persistedProject = projectRepo.save(projectEntity);
		System.err.println("persistedProject e" + persistedProject.getProjectId() +" "+projectDto.getManagerId());
		if(!StringUtils.isEmpty(projectDto.getManagerId())) {
			System.err.println("persistedProject e" + persistedProject.getProjectId() +" "+projectDto.getManagerId());
			userRepo.addProject(persistedProject.getProjectId(), projectDto.getManagerId());
			
		}
	}
	
	@GetMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskDTO> getTasks() {
		List<Task> taskList = taskRepo.findAll();
		System.err.println("taskList list Size" + taskList.size());
		List<TaskDTO> taskDtoList = new ArrayList<TaskDTO>();
		for (Task task : taskList) {
			TaskDTO taskDTO = new TaskDTO();
			BeanUtils.copyProperties(task, taskDTO);
			taskDtoList.add(taskDTO);
		}
		System.err.println("DTO list Size" + taskDtoList.size());
		return taskDtoList;
	}
	
	@PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void createTask(@RequestBody TaskDTO taskDto) {
		Task taskEntity = new Task();
		BeanUtils.copyProperties(taskDto, taskEntity);
		Task persistedTask = taskRepo.save(taskEntity);
		//System.err.println("persistedProject e" + persistedProject.getProjectId() +" "+taskDto.getManagerId());
		
	}
}
