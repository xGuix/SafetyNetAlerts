package com.safetynet.alerts.controller;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@WebMvcTest(controllers = PersonsController.class)
class PersonsControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	PersonService personservice;
	
	static Person personTest;
	
	@BeforeEach
	void setupTest() {
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
	}
	
	@Test
	void whenReadPersons_returnJsonContent() throws Exception {
		
		when(personservice.getAllPersons()).then(RETURNS_DEFAULTS);
	    mockMvc.perform(get("/persons")
	    	.contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isFound());
	}
	
    @Test
    void whenSearchPersonWithNoRequestParam_returnDefault() throws Exception 
    {
		when(personservice.getPersonByName(Mockito.isNull(),Mockito.isNull())).then(RETURNS_SMART_NULLS);
        mockMvc.perform(get("/person")
        	.contentType(MediaType.APPLICATION_JSON))
            	.andExpect(status().isBadRequest());         
    }
 
    @Test
    void whenSearchPersonWithRequestParam_returnCustom() throws Exception
    {	
    	when(personservice.getPersonByName("Guix","DeBrens")).thenReturn(personTest);
        mockMvc.perform(get("/person")
        	.param("firstName", "Guix")
        	.param("lastName", "DeBrens")
        	.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isFound())
        		.andReturn();
    }
    
    @Test
    void whenPostPerson_addNewOne() throws Exception
    {	
    	when(personservice.addPerson(personTest)).thenReturn(personTest);
	    mockMvc.perform(post("/person")
	        .content(objectMapper.writeValueAsString(personTest))
	        .contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isCreated())
	    		.andReturn();
    }
    
    @Test
    void whenPutPerson_updatePerson() throws Exception
    {	
    	when(personservice.updatePerson(personTest)).thenReturn(personTest);
	    mockMvc.perform(put("/person")
	        .content(objectMapper.writeValueAsString(personTest))
	        .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
    }

    @Test
	void whenDeletePersonsContent() throws Exception
    {	
	    mockMvc.perform(delete("/person")
	    	.param("firstName", "Guix")
	        .param("lastName", "DeBrens")
	        .contentType(MediaType.APPLICATION_JSON))
	   			.andExpect(status().isOk());
	}
}