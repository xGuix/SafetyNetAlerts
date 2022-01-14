package com.safetynet.alerts.integration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AlertIntegrationTestIT
{
    @Autowired
    private MockMvc mockMvc;
    
    /*************************************************************************************************
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
    void personsListCoveredByFirestationTest() throws Exception
    {     
        String station = "1";      
        FirestationPersonAlertDto expected;
        expected = alertService.getPersonsListWithChildrenNumberForStation(station);
        
        FirestationPersonAlertDto firestationPersonAlertTest;        	
        firestationPersonAlertTest = alertsController.personsListCoveredByFirestation(station).getBody();

        assertEquals(expected, firestationPersonAlertTest);
        *************************************************************************************************/       
	
	
    @Test
    void personsListCoveredByFirestationTest() throws Exception
    {
       mockMvc.perform(get("/firestationPersonAlert")
        		.param("station", "1"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("644 Gershwin Cir")))
        		.andExpect(content().string(containsString("Duncan")))
        		.andExpect(content().string(containsString("947 E. Rose Dr")))
        		.andExpect(content().string(containsString("Stelzer")))
       			.andReturn();
    }
    
    @Test
    void childrensWithFamilyByAddress() throws Exception
    {
       mockMvc.perform(get("/childAlert")
        		.param("address", "1509 Culver St"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("1509 Culver St")))
        		.andExpect(content().string(containsString("Tenley")))
        		.andExpect(content().string(containsString("Boyd")))
        		.andExpect(content().string(containsString("9")))
        		.andExpect(content().string(containsString("Jacob")))
        		.andExpect(content().string(containsString("Felicia")))
        		.andExpect(content().string(containsString("841-874-6513")))
        		.andReturn();
    }
    
    @Test
    void phoneNumberListByStation() throws Exception
    {
       mockMvc.perform(get("/phoneAlert")
        		.param("station", "1"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("841-874-7784")))
        		.andExpect(content().string(containsString("841-874-7462")))
        		.andExpect(content().string(containsString("841-874-6512")))
        		.andExpect(content().string(containsString("841-874-8547")))
        		.andReturn();
    }
    
    @Test
    void personsListAndFirestationByAddress() throws Exception
    {
       mockMvc.perform(get("/fireAlert")
        		.param("address", "1509 Culver St"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("1509 Culver St")))
        		.andExpect(content().string(containsString("3")))
        		.andExpect(content().string(containsString("Tenley")))
        		.andExpect(content().string(containsString("Boyd")))
        		.andExpect(content().string(containsString("9")))
        		.andExpect(content().string(containsString("841-874-6512")))
        		.andExpect(content().string(containsString("not disclosed")))
        		.andExpect(content().string(containsString("peanut")))
        		.andExpect(content().string(containsString("Felicia")))
        		.andExpect(content().string(containsString("36")))
        		.andExpect(content().string(containsString("841-874-6544")))
        		.andExpect(content().string(containsString("tetracyclaz")))
        		.andExpect(content().string(containsString("xilliathal")))
        		.andReturn();    
    }
    
    
    @Test
    void familliesListByStation() throws Exception
    {
       mockMvc.perform(get("/floodAlert")
       			.param("station", "1"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("644 Gershwin Cir")))
        		.andExpect(content().string(containsString("908 73rd St")))
        		.andExpect(content().string(containsString("947 E. Rose Dr")))
        		.andExpect(content().string(containsString("Peter")))
        		.andExpect(content().string(containsString("Duncan")))
        		.andExpect(content().string(containsString("21")))
        		.andExpect(content().string(containsString("not disclosed")))
        		.andExpect(content().string(containsString("jaboyd@email.com")))
        		.andExpect(content().string(containsString("shellfish")))
        		.andExpect(content().string(containsString("Kendrik")))
        		.andExpect(content().string(containsString("Stelzer")))
        		.andExpect(content().string(containsString("7")))
        		.andExpect(content().string(containsString("not disclosed")))
        		.andExpect(content().string(containsString("bstel@email.com")))
        		.andExpect(content().string(containsString("oxidian")))
        		.andExpect(content().string(containsString("pharmacol")))
        		.andReturn();      
    }
    
    @Test
    void personInfoByName() throws Exception
    {
       mockMvc.perform(get("/personInfo")
       			.param("firstName", "Tenley")
      			.param("lastName", "Boyd"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("Tenley")))
        		.andExpect(content().string(containsString("Boyd")))
        		.andExpect(content().string(containsString("9")))
        		.andExpect(content().string(containsString("841-874-6512")))
        		.andExpect(content().string(containsString("tenz@email.com")))
        		.andExpect(content().string(containsString("peanut")))
        		.andReturn();      
    }
    
    @Test
    void emailsListByCity() throws Exception
    {
       mockMvc.perform(get("/communityEmail")
        		.param("city", "Culver"))
        		.andExpect(status().isOk())
        		.andExpect(content().string(containsString("drk@email.com")))
        		.andExpect(content().string(containsString("soph@email.com")))
        		.andExpect(content().string(containsString("reg@email.com")))
        		.andExpect(content().string(containsString("jaboyd@email.com")))
        		.andExpect(content().string(containsString("bstel@email.com")))
        		.andExpect(content().string(containsString("aly@imail.com")))
        		.andExpect(content().string(containsString("zarc@email.com")))
        		.andExpect(content().string(containsString("tenz@email.com")))
        		.andReturn();      
    }
}