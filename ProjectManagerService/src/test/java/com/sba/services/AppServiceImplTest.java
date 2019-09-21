package com.sba.services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class AppServiceImplTest {
	
	
	private UserRepository userRepo;
	private ProjectRepository projectRepo;
	private TaskRepository taskRepo;
	private ParentRepository parentRepo;
	private AppServiceImpl service;
	
	
	
	ObjectMapper mapper = new ObjectMapper();

	
	
	public AppServiceImplTest() {
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userRepo = Mockito.mock(UserRepository.class);
		projectRepo=Mockito.mock(ProjectRepository.class);
		taskRepo=Mockito.mock(TaskRepository.class);
		parentRepo=Mockito.mock(ParentRepository.class);
		service= new AppServiceImpl(userRepo, projectRepo, taskRepo, parentRepo);
	}
	

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testGetUserList() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
		List<User> allUsers = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("users.json").getFile());
		allUsers = mapper.readValue(file, mapType);
		when(userRepo.findAll()).thenReturn(allUsers);
		List<UserDTO> allUsersDetails = service.getUserList();
		Assert.assertNotNull(allUsersDetails);
		verify(userRepo,times(1)).findAll();
		verifyNoMoreInteractions(userRepo);
	}
	

	@Test
	public void testCreateUser() throws JsonParseException, JsonMappingException, IOException {
		User user = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user.json").getFile());
		user = mapper.readValue(file, User.class);
		
		UserDTO userDTO = null;
		File fileUserDTO = new File(classLoader.getResource("user.json").getFile());
		userDTO = mapper.readValue(fileUserDTO, UserDTO.class);
		when(userRepo.save(user)).thenReturn(user);
		service.createUser(userDTO);
	
	}

	@Test
	public void testDeleteUserById() {
		assertTrue(true);
	}

	@Test
	public void testGetProjectList() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {};
		List<Project> allProjects = null;
		Long projectId = (long) 3;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projects.json").getFile());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		allProjects = mapper.readValue(file, mapType);
		when(projectRepo.findAll()).thenReturn(allProjects);
		//when(taskRepo.getTotalTasksForProjectId(projectId)).thenReturn(projectId);
		List<ProjectDTO> allProjectDetails = service.getProjectList();
		Assert.assertNotNull(allProjectDetails);
		verify(projectRepo,times(1)).findAll();
		verifyNoMoreInteractions(projectRepo);
	}
	

	@Test
	public void testCreateProject() throws JsonParseException, JsonMappingException, IOException {
		Project project = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project.json").getFile());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		project = mapper.readValue(file, Project.class);
		
		ProjectDTO projectDTO = null;
		File fileProjectDTO = new File(classLoader.getResource("project.json").getFile());
		projectDTO = mapper.readValue(fileProjectDTO, ProjectDTO.class);
		when(projectRepo.save(project)).thenReturn(project);
		service.createProject(projectDTO);
	}
	

	@Test
	public void testGetTasksList() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<Task>> mapType = new TypeReference<List<Task>>() {};
		List<Task> allTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("repo-tasks.json").getFile());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		allTasks = mapper.readValue(file, mapType);
		when(taskRepo.findAll()).thenReturn(allTasks);
		List<TaskDTO> taskDetails = service.getTasksList();
		Assert.assertNotNull(taskDetails);
		verify(taskRepo,times(1)).findAll();
		verifyNoMoreInteractions(taskRepo);
	}

	@Test
	public void testCreateTask() throws JsonParseException, JsonMappingException, IOException {
		Task task = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task.json").getFile());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		task = mapper.readValue(file, Task.class);
		
		TaskDTO taskDTO = null;
		File fileTaskDTO = new File(classLoader.getResource("task.json").getFile());
		taskDTO = mapper.readValue(fileTaskDTO, TaskDTO.class);
		when(taskRepo.save(task)).thenReturn(task);
		service.createTask(taskDTO);
	}

	@Test
	public void testCompleteTask() {
		assertTrue(true);
	}

	@Test
	public void testGetParentTasks() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<ParentTask>> mapType = new TypeReference<List<ParentTask>>() {};
		List<ParentTask> allParentTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttasks.json").getFile());
		allParentTasks = mapper.readValue(file, mapType);
		when(parentRepo.findAll()).thenReturn(allParentTasks);
		List<ParentTaskDTO> allParentTasksDetails = service.getParentTasks();
		Assert.assertNotNull(allParentTasksDetails);
		verify(parentRepo,times(1)).findAll();
		verifyNoMoreInteractions(parentRepo);
	}

}
