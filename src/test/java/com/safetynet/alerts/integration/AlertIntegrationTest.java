package com.safetynet.alerts.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
class AlertIntegrationTest
{
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
    void getPersonsListCoveredByFirestationTest()
    {
        String station = "1";
        FirestationPersonAlertDto expected = alertService.getPersonsListWithChildrenNumberForStation(station);
        
        FirestationPersonAlertDto result;        	
        result = alertsController.personsListCoveredByFirestation(station).getBody();

        assertEquals(expected, result);         
    }
/*************************************************************************************************
    
    @Test
    void getAllMedicalRecordsTest()
    {
    	List<MedicalRecord> expected = medicalRecordRepository.getMedicalRecordList();
    	List<MedicalRecord> result;
    	
    	result = medicalRecordsController.getAllMedicalRecords().getBody();

        assertTrue(medicalRecordRepository.getAllMedicalRecords().contains(medicalRecordTest));
        assertEquals(expected, result);
    }

    @Test
    void getOneMedicalRecordWithNameTest()
    {
    	MedicalRecord expected = medicalRecordService.getMedicalRecordByName("Guix","DeBrens");
    	MedicalRecord result;
        
        result = medicalRecordsController.getMedicalRecordByName("Guix","DeBrens").getBody();

        assertTrue(medicalRecordRepository.getAllMedicalRecords().contains(medicalRecordTest));
        assertEquals(expected, result);
    }
    
    @Test
   void addMedicalRecordTest()
    {
    	medicationUDTest = Arrays.asList("xanax:1600mg","oxycodone:350mg","opium:850mg");
    	allergieUDTest = Arrays.asList("God","Vegetables");
    	MedicalRecord medicalRecordToAdd =  new MedicalRecord("Bel","Zebuth","66/66/6666",medicationUDTest,allergieUDTest);
        List <MedicalRecord> expected = medicalRecordRepository.getAllMedicalRecords();
        
        medicalRecordsController.addMedicalRecord(medicalRecordToAdd);
        
        List <MedicalRecord> result = medicalRecordRepository.getMedicalRecordList();
        assertTrue(medicalRecordRepository.getAllMedicalRecords().contains(medicalRecordToAdd));
        assertEquals(expected, result);
    }

    @Test
    void updateMedicalRecordTest()
    {
    	
    	MedicalRecord medicalRecordToEdit =  new MedicalRecord("Guix","DeBrens", null, medicationUDTest, allergieUDTest);
        List<MedicalRecord> expected = medicalRecordRepository.getAllMedicalRecords();

        medicalRecordsController.updateMedicalRecord("Guix","DeBrens",medicalRecordToEdit);
        
        List<MedicalRecord> result = medicalRecordRepository.getMedicalRecordList();
        assertTrue(medicalRecordRepository.getAllMedicalRecords().contains(medicalRecordToEdit));
        assertEquals(expected, result);
    }

    @Test
    void deleteMedicalRecordTest()
    {
        List<MedicalRecord> expected = medicalRecordRepository.getAllMedicalRecords();

        medicalRecordsController.deleteMedicalRecord("Guix","DeBrens");

        List<MedicalRecord> result = medicalRecordRepository.getMedicalRecordList();
        assertFalse(medicalRecordRepository.getAllMedicalRecords().contains(medicalRecordTest));
        assertEquals(expected, result);
    }  
    *************************************************************************************************/ 
}