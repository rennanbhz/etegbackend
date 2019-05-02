package com.eteg.backend.user.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.eteg.backend.user.User;
import com.eteg.backend.user.UserRepository;


/**
 * Unit test to UserController class.
 * 
 * @author Renan Mattos
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserControllerTest.class)

public class UserControllerTest 
{
	@Autowired private MockMvc mvc;
	
	@MockBean private UserRepository userRepository;

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "Renan Mattos Ferreira";
	private static final char USER_GENDER = 'm';
	private static final String USER_BIRTH_DATE = "19/04/1988";
	private static final String USER_CPF = "12312345342";
	
	private User mockUser = new User(USER_ID, USER_NAME, USER_GENDER, USER_BIRTH_DATE, USER_CPF, null);
	private String mockUserJson =
		      "{\"userId\":1\",\"name\":\"Renan Mattos Ferreira\",\"gender\":\"m\",\"birthDate\":\"19/04/1988\",\"cpf\":\"44009055542\"}";
	
	@Test
	public void createUser_withValidInformation_returnCreated() throws Exception
	{
		when(userRepository.save(Mockito.any())).thenReturn(mockUser);
		
		mvc.perform(
	            post("/user")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(mockUserJson)
	                .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.userId", is(USER_ID)));
	}
	
	@Test
	public void deleteuser_withValidInformation_returnSuccess() throws Exception
	{
		 when(userRepository.findById(USER_ID)).thenReturn(Optional.of(mockUser));
		    when(userRepository.save(Mockito.any())).thenReturn(mockUser);

		    mvc.perform(
		            put("/user/" + USER_ID)
		                .accept(MediaType.APPLICATION_JSON)
		                .content(mockUserJson)
		                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isNoContent());
	}
	
	@Test
	public void searchForAllUsers_withoutFilter_shouldReturnUserList() throws Exception
	{
		List<User> userList = Collections.singletonList(mockUser);

	    when(userRepository.findAll()).thenReturn(userList);

	    mvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(1)))
	        .andExpect(jsonPath("$[0].userId", is(mockUser.getUserId())));
	}
	
	@Test
	public void searchForAllUsers_filteringByuserId_shouldReturnUserList() throws Exception {

	    when(userRepository.findById(USER_ID)).thenReturn(Optional.of(mockUser));

	    mvc.perform(get("/user/" + USER_ID).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.name", is(mockUser.getUserId())));
	  }
	
	@Test
	public void deleteUser_withValidInformation_returnOKStatus() throws Exception {

	    mvc.perform(
	            delete("/user/" + USER_ID)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(mockUserJson)
	                .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	  }
}
