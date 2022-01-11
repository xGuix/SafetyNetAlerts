package com.safetynet.alerts.controller;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.dto.FireAlertDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.AlertService;

@WebMvcTest(controllers = AlertsController.class)
class AlertsControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AlertService alertService;

	static Firestation firestation;
	static FireAlertDto fireAlertTest;
	static FireAlertDto fireAlert;
	static List<PersonInfoDto> personInfoList;

	@BeforeEach
	void setupTest() {
		fireAlert = new FireAlertDto(firestation,personInfoList);
		fireAlertTest = new FireAlertDto();		
		fireAlertTest.setFirestation(firestation);
		fireAlertTest.setPersonInfoList(personInfoList);
	}
	
	@Test
	void whenCallFirestationAlert() throws Exception {
		
		when(alertService.getPersonsListWithChildrenNumberForStation("1"))
				.then(RETURNS_DEFAULTS);
		
	    mockMvc.perform(get("/firestationPersonAlert")
	    		.contentType(MediaType.APPLICATION_JSON)
				.param("station", "1"))
	        	.andExpect(status().isOk())
	    		.andReturn();
	}
	
    @Test
    void whenCallChildAlert() throws Exception 
    {
		when(alertService.getChildrenWithFamilyListAtAddress("1509 Culver St"))
				.then(RETURNS_DEFAULTS);
		
        mockMvc.perform(get("/childAlert")
				.contentType(MediaType.APPLICATION_JSON)
				.param("address", "1509 Culver St"))
				.andExpect(status().isOk())
				.andReturn();         
    }
 
    @Test
    void whenCallPhoneAlert() throws Exception 
    {
		when(alertService.getEmailsListByCity("1")).then(RETURNS_DEFAULTS);
        mockMvc.perform(get("/phoneAlert")
				.contentType(MediaType.APPLICATION_JSON)
				.param("station", "1"))
				.andExpect(status().isOk())
				.andReturn();         
    }
    
    @Test
    void whenCallFireAlert() throws Exception
    {	
    	when(alertService.getPersonsListAndFirestationForAddress("1509 Culver St"))
    			.thenReturn(fireAlertTest, fireAlertTest);
    	
        mockMvc.perform(get("/fireAlert")
            	.contentType(MediaType.APPLICATION_JSON)
        		.param("address", "1509 Culver St"))
        		.andExpect(status().isOk())
        		.andReturn();
    }
    
    @Test
    void whenCallFloodAlert() throws Exception 
    {
		when(alertService.getHomeFamilyforStation("1"))
				.then(RETURNS_DEFAULTS);
		
        mockMvc.perform(get("/floodAlert")
				.contentType(MediaType.APPLICATION_JSON)
				.param("station", "1"))
				.andExpect(status().isOk())
				.andReturn();         
    }
    
    @Test
    void whenCallPersonInfo() throws Exception 
    {
		when(alertService.getPersonInfo("John","Boyd"))
				.then(RETURNS_DEFAULTS);
		
        mockMvc.perform(get("/personInfo")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "John")
				.param("lastName", "Boyd"))
				.andExpect(status().isOk())
				.andReturn();         
    }
    
    @Test
    void whenCallCommunityEmail() throws Exception 
    {
		when(alertService.getEmailsListByCity("Culver"))
				.then(RETURNS_DEFAULTS);
		
        mockMvc.perform(get("/communityEmail")
				.contentType(MediaType.APPLICATION_JSON)
				.param("city", "Culver"))
				.andExpect(status().isOk())
				.andReturn();         
    }
}