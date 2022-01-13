package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Firestation;

@SpringBootTest
class FirestationRepositoryTest
{
	@Autowired
    FirestationRepository firestationRepository;
	
	public static List<Firestation> firestationListTest;
	
	Firestation firestationTest;
	Firestation firestationTestAddress;
	Firestation firestationTestStation;

	@BeforeAll
	public static void setupTestForAll()
	{
		firestationListTest = new ArrayList<>();
	}
	
	@BeforeEach
	void setupTestForEach()
	{
		firestationTest = new Firestation("Saint Omer sur Mer", "1");
		firestationTestAddress = new Firestation("TestAddress","TestNumber");
		firestationTestStation = new Firestation("Saint Omer sur Mer","TestNumber");
	}
	
	@Test
	void TestIfGetAllFirestationReturnPersonList()
	{
		List <Firestation> newFirestationListTest = firestationRepository.getAllFirestation();
		
		assertEquals(newFirestationListTest, firestationRepository.getAllFirestation());
	}
	
	@Test
	void TestAddPersonReturnPerson()
	{
		Firestation newFirestationAddTest = firestationRepository.addFirestation(firestationTest);

		assertEquals(newFirestationAddTest, firestationTest);
	}

	@Test
	void TestUpdateFirestationWhenExists()
	{
		firestationListTest.add(firestationTest);
		assertThrows(NotFoundException.class,() -> firestationRepository.updateFirestation("Saint Omer sur Mer", firestationTest));
	}
	
	@Test
	void TestUpdateFirestationWhenIsWrongAddress()
	{
		firestationListTest.add(firestationTest);
		assertThrows(NotFoundException.class, () -> firestationRepository.updateFirestation("TestAddress",firestationTestAddress));
	}

	@Test
	void TestDeleteFirestationRemovePersonFromList()
	{
		firestationListTest.add(firestationTest);
		firestationRepository.deleteFirestation(firestationTest);
		assertTrue(firestationListTest.remove(firestationTest));
	}
}