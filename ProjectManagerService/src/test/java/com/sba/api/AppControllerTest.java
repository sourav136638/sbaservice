package com.sba.api;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sba.dto.ParentTaskDTO;
import com.sba.dto.ProjectDTO;
import com.sba.dto.TaskDTO;
import com.sba.dto.UserDTO;
import com.sba.services.AppServiceImpl;

@RunWith(SpringRunner.class)

public class AppControllerTest {


	private AppController appController;

	private AppServiceImpl service;	 

	ObjectMapper mapper = new ObjectMapper();  
	


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {
		service = Mockito.mock(AppServiceImpl.class);
		appController = new AppController(service);
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUsers() throws Exception {
		TypeReference<List<UserDTO>> mapType = new TypeReference<List<UserDTO>>() {};
		List<UserDTO> allTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("users.json").getFile());
		allTasks = mapper.readValue(file, mapType);
		when(service.getUserList()).thenReturn(allTasks);
		List<UserDTO> allTasksList = appController.getUsers();
		Assert.assertNotNull(allTasks);
		verify(service,times(1)).getUserList();
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testCreateUser() throws JsonParseException, JsonMappingException, IOException {
		UserDTO user = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user.json").getFile());
		user = mapper.readValue(file, UserDTO.class);
		doNothing().when(service).createUser(user);
		appController.createUser(user);
		verify(service,times(1)).createUser(user);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testDeleteUserById() {
		assertTrue(true);
	}

	@Test
	public void testGetProjects() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<ProjectDTO>> mapType = new TypeReference<List<ProjectDTO>>() {};
		List<ProjectDTO> allProjects = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projects.json").getFile());
		allProjects = mapper.readValue(file, mapType);
		when(service.getProjectList()).thenReturn(allProjects);
		List<ProjectDTO> allProjectsList = appController.getProjects();
		Assert.assertNotNull(allProjectsList);
		verify(service,times(1)).getProjectList();
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testCreateProject()  throws JsonParseException, JsonMappingException, IOException {
		ProjectDTO project = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("project.json").getFile());
		project = mapper.readValue(file, ProjectDTO.class);
		doNothing().when(service).createProject(project);
		appController.createProject(project);
		verify(service,times(1)).createProject(project);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetTasks() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<TaskDTO>> mapType = new TypeReference<List<TaskDTO>>() {};
		List<TaskDTO> allTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("tasks.json").getFile());
		allTasks = mapper.readValue(file, mapType);
		when(service.getTasksList()).thenReturn(allTasks);
		List<TaskDTO> allTasksList = appController.getTasks();
		Assert.assertNotNull(allTasksList);
		verify(service,times(1)).getTasksList();
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testCreateTask() throws JsonParseException, JsonMappingException, IOException {
		TaskDTO task = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("task.json").getFile());
		task = mapper.readValue(file, TaskDTO.class);
		doNothing().when(service).createTask(task);
		appController.createTask(task);
		verify(service,times(1)).createTask(task);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetParentTasks() throws JsonParseException, JsonMappingException, IOException {
		TypeReference<List<ParentTaskDTO>> mapType = new TypeReference<List<ParentTaskDTO>>() {};
		List<ParentTaskDTO> allParentTasks = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttasks.json").getFile());
		allParentTasks = mapper.readValue(file, mapType);
		when(service.getParentTasks()).thenReturn(allParentTasks);
		List<ParentTaskDTO> allParentTasksList = appController.getParentTasks();
		Assert.assertNotNull(allParentTasksList);
		verify(service,times(1)).getParentTasks();
		verifyNoMoreInteractions(service);
	}

}
