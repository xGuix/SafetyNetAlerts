package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.MedicalRecord;

@SpringBootTest
class MedicalRecordRepositoryTest
{
	@Autowired
    MedicalRecordRepository medicalRecordRepository;
	
	public static List<MedicalRecord> medicalRecordListTest;
	public static List<String> medicationUD;
	public static List<String> allergieUD;
	MedicalRecord medicalRecordTest;
	MedicalRecord medicalRecordTestForFirstName;
	MedicalRecord medicalRecordTestForLastName;

	@BeforeAll
	public static void setupTestForAll()
	{
		medicalRecordListTest = new ArrayList<>();
		medicalRecordListTest = new ArrayList<>();
		medicationUD = Arrays.asList("Alcohol: 2 bottles of Whisky","Tabacco: 5 packets","Doner-kebbab: 3 times a day");
		allergieUD = Arrays.asList("Liar","Storyteller");
	}
	
	@BeforeEach
	void setupTestForEach()
	{
		medicalRecordTest = new MedicalRecord("Guix","DeBrens","22/10/1982",medicationUD, allergieUD);
		medicalRecordTestForFirstName = new MedicalRecord("Belzebuth","DeBrens",null,null,null);
		medicalRecordTestForLastName = new MedicalRecord("Guix","Belzebuth",null,null,null);
	}
	
	@Test
	void TestIfGetAllPersonReturnPersonList()
	{
		List <MedicalRecord> newMedicalRecordListTest = medicalRecordRepository.getAllMedicalRecords();
		
		assertEquals(newMedicalRecordListTest, medicalRecordRepository.getAllMedicalRecords());

	}
	
	@Test
	void TestAddPersonReturnPerson()
	{
		MedicalRecord newMedicalRecordAddTest = medicalRecordRepository.addMedicalRecord(medicalRecordTest);

		assertEquals(newMedicalRecordAddTest, medicalRecordTest);
	}

	@Test
	void TestUpdatePersonWhenExists()
	{
		medicalRecordListTest.add(medicalRecordTest);
		
		assertThrows(NotFoundException.class,() -> medicalRecordRepository.updateMedicalRecord("Guix","DeBrens", medicalRecordTest));
	}
	
	@Test
	void TestUpdatePersonWhenIsWrong()
	{
		medicalRecordListTest.add(medicalRecordTest);

		assertDoesNotThrow(() -> medicalRecordRepository.updateMedicalRecord("Guix","DeBrens",medicalRecordTestForLastName));
	}
	
	@Test
	void TestDeletePersonRemovePersonFromList()
	{
		medicalRecordListTest.add(medicalRecordTest);
		medicalRecordRepository.deleteMedicalRecord(medicalRecordTest);
		
		assertTrue(medicalRecordListTest.remove(medicalRecordTest));
	}
}