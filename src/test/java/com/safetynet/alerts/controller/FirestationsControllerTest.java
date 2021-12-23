package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.service.FirestationService;

@WebMvcTest(controllers = FirestationsController.class)
class FirestationsControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	FirestationService firestationService;
		
	@Test
	void whenReadPersons_returnJsonContent() throws Exception {
		
	    mockMvc.perform(get("/firestations").contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isFound());
	}
	
    @Test
    void whenNoRequestParam_returnDefault() throws Exception 
    {
        mockMvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest());         
    }
 
    @Test
    void whenRequestParam_returnCustom() throws Exception
    {	    	
        mockMvc.perform(get("/firestation").param("address", "").param("station", "")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isFound())
        		.andReturn();
    }
    
    @Test
    void whenPostPerson_addNewOne() throws Exception
    {	    	
	    mockMvc.perform(post("/firestation").content(objectMapper.writeValueAsString(objectMapper))
	    		.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isCreated());
    }
    
    @Test
    void whenPutPerson_updatePerson() throws Exception
    {	    	
	    mockMvc.perform(put("/firestation").content(objectMapper.writeValueAsString(objectMapper))
	    		.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isOk());
    }

    @Test
	void whenDeletePersonsContent() throws Exception
    {	
	    mockMvc.perform(delete("/firestation").param("address", "1").param("station", "")
	    		.contentType(MediaType.APPLICATION_JSON))
	   			.andExpect(status().isOk());
	}
}