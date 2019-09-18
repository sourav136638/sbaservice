package com.sba.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sba.dto.ParentTaskDTO;
import com.sba.dto.ProjectDTO;
import com.sba.dto.TaskDTO;
import com.sba.dto.UserDTO;
import com.sba.services.AppServiceImpl;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AppController {
	private static final Logger log = LoggerFactory.getLogger(AppController.class);
	
	@Autowired 
	AppServiceImpl service;
	

	@GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getUsers() {		
		return service.getUserList();
	}

	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody UserDTO userDto) {
		service.createUser(userDto);
	}

	@DeleteMapping(value = "/user/{id}")
	public void deleteUserById(@PathVariable long id) {
		service.deleteUserById(id);
	}

	
	@GetMapping(path = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDTO> getProjects() {		
		return service.getProjectList();
	}

	@PostMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void createProject(@RequestBody ProjectDTO projectDto) {
		service.createProject(projectDto);
	}

	@GetMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskDTO> getTasks() {			
		return service.getTasksList();
	}

	@PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createTask(@RequestBody TaskDTO taskDto) {
		service.createTask(taskDto);
	}

	@GetMapping(path = "/parenttask", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParentTaskDTO> getParentTasks() {		
		return service.getParentTasks();
	}
}
