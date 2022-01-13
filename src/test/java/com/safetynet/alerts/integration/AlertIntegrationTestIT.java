package com.safetynet.alerts.integration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.AlertsController;
import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.AlertService;
import com.safetynet.alerts.service.FirestationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;

@SpringBootTest
@AutoConfigureMockMvc
class AlertIntegrationTestIT
{
    @Autowired
    private MockMvc mockMvc;
	
	PersonRepository personRepository = new PersonRepository();
	FirestationRepository firestationRepository = new FirestationRepository();
	MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

	FirestationService firestationService = new FirestationService();
	PersonService personService = new PersonService();
	MedicalRecordService medicalRecordService = new MedicalRecordService();

	AlertService alertService = new AlertService();
	AlertsController alertsController = new AlertsController();

	@BeforeEach
	public void setupTest()
	{
        personRepository.setPersonList(personRepository.getPersonList());
	    firestationRepository.setFirestationList(firestationRepository.getFirestationList());
	    medicalRecordRepository.setMedicalRecordList(medicalRecordRepository.getMedicalRecordList());

	    personService.setPersonRepository(personRepository);
	    firestationService.setFirestationRepository(firestationRepository);
	    medicalRecordService.setMedicalRecordRepository(medicalRecordRepository);

	    alertService.setFirestationService(firestationService);
	    alertService.setPersonService(personService);
	    alertService.setMedicalRecordService(medicalRecordService);

	    alertsController.setAlertService(alertService);
	}
		
    @Test
    void getPersonsListCoveredByFirestationTest() throws Exception
    {
        mockMvc.perform(get("/firestationPersonAlert")
        		.param("station", "1"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("644 Gershwin Cir")))
        		.andExpect(content().string(containsString("Duncan")))
        		.andExpect(content().string(containsString("947 E. Rose Dr")))
        		.andExpect(content().string(containsString("Stelzer")));
        
        String station = "1";      
        FirestationPersonAlertDto expected;
        expected = alertService.getPersonsListWithChildrenNumberForStation(station);
        
        FirestationPersonAlertDto firestationPersonAlertTest;        	
        firestationPersonAlertTest = alertsController.personsListCoveredByFirestation(station).getBody();

        assertEquals(expected, firestationPersonAlertTest);         
    }
    
    
}