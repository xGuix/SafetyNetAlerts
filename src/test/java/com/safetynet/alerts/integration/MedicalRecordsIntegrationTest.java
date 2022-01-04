package com.safetynet.alerts.integration;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.controller.MedicalRecordsController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.MedicalRecordService;

@SpringBootTest
class MedicalRecordsIntegrationTest
{
	MedicalRecordsController medicalRecordsController= new MedicalRecordsController();
	MedicalRecordService medicalRecordService = new MedicalRecordService();
	MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
	MedicalRecord medicalRecordTest;
	List<String> medicationTest;
	List<String> allergieTest;
	List<String> medicationUDTest;
	List<String> allergieUDTest;
    
    @BeforeEach
    public void setupTest()
    {
    	medicalRecordRepository.setMedicalRecordList(medicalRecordRepository.getMedicalRecordList());
    	medicalRecordService.setMedicalRecordRepository(medicalRecordRepository);
    	medicalRecordsController.setMedicalRecordsService(medicalRecordService);
    	
    	medicationTest = Arrays.asList("Alcohol: 2 bottles of Whisky","Tabacco: 5 packets","Doner-kebbab: 3 times a day");
		allergieTest = Arrays.asList("Liar","Storyteller");
		
    	medicalRecordTest = new MedicalRecord("Guix","DeBrens", "22/10/1982",medicationTest,allergieTest);
    	medicalRecordRepository.addMedicalRecord(medicalRecordTest);
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
}