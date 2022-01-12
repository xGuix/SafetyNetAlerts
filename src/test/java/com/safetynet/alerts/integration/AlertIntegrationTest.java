package com.safetynet.alerts.integration;

import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.controller.AlertsController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.AlertService;

@SpringBootTest
class AlertIntegrationTest
{
	AlertsController alertsController= new AlertsController();
	AlertService alertService = new AlertService();

	MedicalRecord medicalRecordTest;
	List<String> medicationTest;
	List<String> allergieTest;

/*************************************************************************************************
    @BeforeEach
    public void setupTest()
    {
    	medicalRecordRepository.setMedicalRecordList(medicalRecordRepository.getMedicalRecordList());
    	medicalRecordService.setMedicalRecordRepository(medicalRecordRepository);
    	AlertsController.setAlerts(alertService);
    	    }
    
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