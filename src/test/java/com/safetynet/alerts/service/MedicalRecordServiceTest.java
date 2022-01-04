package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.exception.AlreadyExistingException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest
{	
    @InjectMocks
    MedicalRecordService medicalRecordService;
    
    @Mock
    MedicalRecordRepository medicalRecordRepository;
    
	List<MedicalRecord> medicalRecordListTest;
	List<String> medicationUD;
	List<String> allergieUD;
	MedicalRecord medicalRecordTest;
	MedicalRecord medicalRecordTestFirstName;
	MedicalRecord medicalRecordTestLastName;
	
	@BeforeEach
	void setupTest()
	{
		medicalRecordListTest = new ArrayList<>();
		medicationUD = Arrays.asList("Alcohol: 2 bottles of Whisky","Tabacco: 5 packets","Doner-kebbab: 3 times a day");
		allergieUD = Arrays.asList("Liar","Storyteller");
		medicalRecordTest = new MedicalRecord("Guix","DeBrens", "22/10/1982",medicationUD,allergieUD);
		medicalRecordTestFirstName = new MedicalRecord("TestFirstName","TestLastName", null,null,null);
		medicalRecordTestLastName = new MedicalRecord("Guix","TestLastName", null,null,null);
	}
	
	@Test
	void TestIfGetAllMedicalRecordsReturnMedicalRecordsList()
	{
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		List<MedicalRecord> allMedicalRecordsListTest = medicalRecordService.getAllMedicalRecords();
		
		assertEquals(allMedicalRecordsListTest, medicalRecordListTest);
		verify(medicalRecordRepository, Mockito.times(1)).getAllMedicalRecords();
	}
	
	@Test
	void TestIfGetMedicalRecordReturnMedicalRecordWithName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		MedicalRecord medicalRecordToTest = medicalRecordService.getMedicalRecordByName("Guix","DeBrens");
		assertEquals(medicalRecordToTest,medicalRecordTest);
	}
	
	@Test
	void TestGetMedicalRecordWithEmptyList()
	{
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		assertThrows(NotFoundException.class, () -> medicalRecordService.getMedicalRecordByName("Guix","DeBrens"));
	}
	
	@Test
	void TestGetMedicalRecordWithWrongName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		assertThrows(NotFoundException.class, () -> medicalRecordService.getMedicalRecordByName("FirstNameTest","LastNameTest"));
	}
	
	@Test
	void TestGetMedicalRecordWithWrongLastName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		assertThrows(NotFoundException.class, () -> medicalRecordService.getMedicalRecordByName("Guix","LastNameTest"));
	}
	
	@Test
	void TestAddMedicalRecordWhenDoesNotExists()
	{
		when(medicalRecordRepository.addMedicalRecord(medicalRecordTest)).thenReturn(medicalRecordTest);
		
		MedicalRecord medicalRecordToAddTest = medicalRecordService.addMedicalRecord(medicalRecordTest);
		assertEquals(medicalRecordToAddTest, medicalRecordRepository.addMedicalRecord(medicalRecordTest));
		verify(medicalRecordRepository, Mockito.times(2)).addMedicalRecord(medicalRecordTest);
	}
	
	@Test
	void TestAddMedicalRecordWhenAlreadyExists()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		assertThrows(AlreadyExistingException.class, () -> medicalRecordService.addMedicalRecord(medicalRecordTest));
	}
	
	@Test
	void TestAddMedicalRecordWhenWrongFirstName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		assertDoesNotThrow(() -> medicalRecordService.addMedicalRecord(medicalRecordTestFirstName));
	}
	
	@Test
	void TestAddMedicalRecordWhenWrongLastName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		assertDoesNotThrow(() -> medicalRecordService.addMedicalRecord(medicalRecordTestLastName));
	}
	
	@Test
	void TestIfUpdateMedicalRecordSendMedicalRecord()
	{
		when(medicalRecordRepository.updateMedicalRecord("Guix","DeBrens",medicalRecordTest)).thenReturn(medicalRecordTest);
		
		MedicalRecord medicalRecordToUpdateTest = medicalRecordService.updateMedicalRecord("Guix","DeBrens",medicalRecordTest);
		assertEquals(medicalRecordToUpdateTest, medicalRecordRepository.updateMedicalRecord("Guix","DeBrens",medicalRecordTest));
		verify(medicalRecordRepository, Mockito.times(2)).updateMedicalRecord("Guix","DeBrens",medicalRecordTest);
	}
	
	@Test
	void TestDeleteMedicalRecordIfExists()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);
		
		medicalRecordService.deleteMedicalRecord(medicalRecordTest.getFirstName(),medicalRecordTest.getLastName());
		
		assertTrue(medicalRecordListTest.remove(medicalRecordTest));
		verify(medicalRecordRepository, Mockito.times(1)).deleteMedicalRecord(medicalRecordTest);
	}
	
	@Test
	void TestDeleteMedicalRecordWhenWrongFirstName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);	
		
		assertThrows(NotFoundException.class, () -> medicalRecordService.deleteMedicalRecord("TestFirstName","TestLastName"));
	}
	
	@Test
	void TestDeleteMedicalRecordWhenWrongLastName()
	{
		medicalRecordListTest.add(medicalRecordTest);
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);	
		
		assertThrows(NotFoundException.class, () -> medicalRecordService.deleteMedicalRecord("Guix","TestLastName"));
	}
	
	@Test
	void TestDeleteMedicalRecordIfNotFound()
	{
		when(medicalRecordRepository.getAllMedicalRecords()).thenReturn(medicalRecordListTest);	
		
		assertThrows(NotFoundException.class, () -> medicalRecordService.deleteMedicalRecord("Guix","DeBrens"));
	}
}