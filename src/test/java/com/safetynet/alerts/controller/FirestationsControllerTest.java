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
import com.safetynet.alerts.model.Firestation;
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
	
	static Firestation firestationTest;
	
	@BeforeEach
	void setupTest()
	{
		firestationTest = new Firestation("Saint Omer sur Mer","1");
	}
	
	@Test
	void whenReadFirestations_returnJsonContent() throws Exception
	{
		when(firestationService.getAllFirestations()).then(RETURNS_DEFAULTS);
	    mockMvc.perform(get("/firestations")
	    	.contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isFound());
	}
	
    @Test
    void whenSearchFirestationWithNoRequestParam_returnDefault() throws Exception 
    {
		when(firestationService.getOneFirestation(Mockito.isNull(),Mockito.isNull())).then(RETURNS_SMART_NULLS);
        mockMvc.perform(get("/firestation")
        	.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest());         
    }
 
    @Test
    void whenSearchFirestationWithRequestParam_returnOneFirestation() throws Exception
    {	
    	when(firestationService.getOneFirestation("Saint Omer sur Mer","1")).thenReturn(firestationTest);
        mockMvc.perform(get("/firestation")
        	.param("address", "Saint Omer Sur Mer")
        	.param("station", "1")
        	.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isFound())
        		.andReturn();
    }
    
    @Test
    void whenPostFirestation_addNewOne() throws Exception
    {	
    	when(firestationService.addFirestation(firestationTest)).thenReturn(firestationTest);
	    mockMvc.perform(post("/firestation")
	    	.content(objectMapper.writeValueAsString(firestationTest))
	    	.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isCreated())
	    		.andReturn();
    }
    
    @Test
    void whenPutFirestation_updatePerson() throws Exception
    {	
    	when(firestationService.updateFirestation("Saint Omer sur Mer", firestationTest)).thenReturn(firestationTest);
	    mockMvc.perform(put("/firestation")
	    	.content(objectMapper.writeValueAsString(firestationTest))
	    	.param("address", "Saint Omer Sur Mer")
	    	.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isOk())
	    		.andReturn();
    }

    @Test
	void whenDeleteFirestationContent() throws Exception
    {	
	    mockMvc.perform(delete("/firestation")
	    	.param("address", "Saint Omer Sur Mer")
	    	.param("station", "1")
	    	.contentType(MediaType.APPLICATION_JSON))
	   			.andExpect(status().isOk());
	}
}