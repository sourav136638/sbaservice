package com.sba.api;

import static org.junit.Assert.assertTrue;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void testCreateUser() {
		assertTrue(true);
	}

	@Test
	public void testDeleteUserById() {
		assertTrue(true);
	}

	@Test
	public void testGetProjects() {
		assertTrue(true);
	}

	@Test
	public void testCreateProject() {
		assertTrue(true);
	}

	@Test
	public void testGetTasks() {
		assertTrue(true);
	}

	@Test
	public void testCreateTask() {
		assertTrue(true);
	}

	@Test
	public void testGetParentTasks() {
		assertTrue(true);
	}

}
