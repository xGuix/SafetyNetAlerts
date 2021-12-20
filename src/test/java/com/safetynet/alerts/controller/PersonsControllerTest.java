package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@WebMvcTest(controllers = PersonsController.class)
class PersonsControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PersonService personservice;
	
	static Person personTest;
	
	@BeforeEach
	void setupTest() {
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
	}
	
	@Test
	void whenReadPersonsList_returnJsonContent() throws Exception {
		
		when(personservice.getAllPersons()).then(RETURNS_MOCKS);
	    mockMvc.perform(get("/persons"))
	        .andExpect(status().isFound())
			.andExpect(view().name("getAllPersons"))
	    	.andDo(print());
	}
	
    @Test
    void whenNoRequestParam_returnDefault() throws Exception 
    {
		when(personservice.getPersonByName(null,null)).then(RETURNS_DEFAULTS);
        mockMvc.perform(get("/person"))
            .andExpect(status().isFound())
			.andExpect(view().name("getPersonByName"))
            .andExpect(content().string(containsString(null)));
            
    }
 
    @Test
    void whenRequestParam_returnCustom() throws Exception
    {	    	
	
        mockMvc.perform(get("/person")
        	.param("firstName", "John")
        	.param("lastName", "Boyd"))
        	.andExpect(status().isFound())
			.andExpect(view().name("getPersonByName"))
        	.andExpect(content().string(containsString("Culver")))
        	.andDo(print());
    }
   
	@Test
	void JustePourVoirTest() throws Exception {}
}