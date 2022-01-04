package com.safetynet.alerts.controller;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordsController.class)
class MedicalRecordsControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	MedicalRecordService medicalRecordService;
	
	static MedicalRecord medicalRecordTest;
	List<String> medication= Arrays.asList("Fentanyl:650mg","Oxycodone:350mg","Opium:900mg");
	List<String> allergie= Arrays.asList("Vegetables","Dickheads");

	@BeforeEach
	void setupTest()
	{
		medicalRecordTest = new MedicalRecord("Guix","DeBrens", "22/10/1982", medication, allergie);
	}
	
	@Test
	void whenReadMedicalRecords_returnJsonContent() throws Exception
	{
		when(medicalRecordService.getAllMedicalRecords()).then(RETURNS_DEFAULTS);
		
	    mockMvc.perform(get("/medicalRecords")
	    	.contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isFound());
	}
	
    @Test
    void whenSearchMedicalRecordsWithNoRequestParam_returnDefault() throws Exception 
    {
		when(medicalRecordService.getMedicalRecordByName(Mockito.isNull(),Mockito.isNull())).then(RETURNS_SMART_NULLS);
		
        mockMvc.perform(get("/medicalRecord")
        	.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest());         
    }
 
    @Test
    void whenSearchMedicalRecordWithRequestParam_returnOneMedicalRecord() throws Exception
    {	
    	when(medicalRecordService.getMedicalRecordByName("Guix","DeBrens")).thenReturn(medicalRecordTest);
    	
        mockMvc.perform(get("/medicalRecord")
        	.param("firstName", "Guix")
        	.param("lastName", "DeBrens")
        	.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isFound())
        		.andReturn();
    }
    
    @Test
    void whenPostMedicalRecord_addNewOne() throws Exception
    {	
    	when(medicalRecordService.addMedicalRecord(medicalRecordTest)).thenReturn(medicalRecordTest);
    	
	    mockMvc.perform(post("/medicalRecord")
	    	.content(objectMapper.writeValueAsString(medicalRecordTest))
	    	.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isCreated())
	    		.andReturn();
    }
    
    @Test
    void whenPutMedicalRecord_updatePerson() throws Exception
    {	
    	final MedicalRecord medicalRecordTestUD;
    	List<String> medicationUD= Arrays.asList("Alcohol: 2 bottles of Whisky","Tabacco: 5 packets","Doner-kebbab: 3 times a day");
    	List<String> allergieUD= Arrays.asList("Liar","Storyteller");
    	medicalRecordTestUD = new MedicalRecord("Guix","DeBrens", "22/10/1982", medicationUD, allergieUD);
    	
    	when(medicalRecordService.updateMedicalRecord(medicalRecordTest.getFirstName(),medicalRecordTest.getLastName(),
    			medicalRecordTestUD)).thenReturn(medicalRecordTestUD);
    	
	    mockMvc.perform(put("/medicalRecord")
	    	.param("firstName", "Guix")
        	.param("lastName", "DeBrens")
        	.content(objectMapper.writeValueAsString(medicalRecordTestUD))
	    	.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isOk())
	    		.andReturn();
    }

    @Test
	void whenDeleteMedicalRecordContent() throws Exception
    {	
	    mockMvc.perform(delete("/medicalRecord")
	    	.param("firstName", "Guix")
	        .param("lastName", "DeBrens")
	    	.contentType(MediaType.APPLICATION_JSON))
	   			.andExpect(status().isOk());
	}
}