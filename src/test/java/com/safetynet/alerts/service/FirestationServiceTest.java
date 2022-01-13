package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;

@ExtendWith(MockitoExtension.class)
class FirestationServiceTest
{	
    @InjectMocks
    FirestationService firestationService;
    
    @Mock
    FirestationRepository firestationRepository;
    
	List<Firestation> firestationListTest;
	Firestation firestationTest;
	Firestation firestationTestForFilter;
	
	@BeforeEach
	void setupTest()
	{
		firestationListTest = new ArrayList<>();
		firestationTest = new Firestation("Saint Omer Sur Mer","1");
		firestationTestForFilter = new Firestation("AddressTest","NumberTest");
	}
	
	@Test
	void TestIfGetAllFirestationReturnFirestationList()
	{
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		List<Firestation> allFirestationListTest = firestationService.getAllFirestations();
		
		assertEquals(allFirestationListTest, firestationListTest);
		verify(firestationRepository, Mockito.times(1)).getAllFirestation();
	}

	@Test
	void TestIfGetFirestationWithStationReturnFirestationList()
	{
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		List<Firestation> firestationsListToTest = firestationService.getFirestationsFor("1");
		assertEquals(firestationsListToTest, firestationListTest);
	}
	
	@Test
	void TestIfGetFirestationWithStationReturnOnlyAddressesList()
	{
		List<String> AddressesListTest = new ArrayList<>();
		AddressesListTest.add(firestationTest.getAddress());
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		List<String> firestationAddressListToTest = firestationService.getOnlyAddressesFor("1");
		assertEquals(firestationAddressListToTest, AddressesListTest);
	}
	
	@Test
	void TestIfGetOneFirestationReturnFirestationWithAddressAndStation()
	{
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		Firestation firestationToTest = firestationService.getOneFirestation("Saint Omer Sur Mer");
		assertEquals(firestationToTest,firestationTest);
	}
	
	@Test
	void TestGetOneFirestationWithEmptyList()
	{
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		assertThrows(NotFoundException.class, () -> firestationService.getOneFirestation("Saint Omer Sur Mer"));
	}
	
	@Test
	void TestAddFirestationWhenDoesNotExists()
	{
		when(firestationRepository.addFirestation(firestationTest)).thenReturn(firestationTest);
		
		Firestation firestationToUpdateTest = firestationService.addFirestation(firestationTest);
		assertEquals(firestationToUpdateTest, firestationRepository.addFirestation(firestationTest));
		verify(firestationRepository, Mockito.times(2)).addFirestation(firestationTest);
	}
	
	@Test
	void TestAddFirestationWhenAlreadyExists()
	{
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		assertThrows(AlreadyExistingException.class, () -> firestationService.addFirestation(firestationTest));
	}
	
	@Test
	void TestIfUpdateFirestationSendFirestation()
	{
		when(firestationRepository.updateFirestation(firestationTest.getAddress(),firestationTest)).thenReturn(firestationTest);
		
		Firestation firestationToUpdateTest = firestationService.updateFirestation(firestationTest.getAddress(),firestationTest);
		assertEquals(firestationToUpdateTest, firestationRepository.updateFirestation(firestationTest.getAddress(),firestationTest));
		verify(firestationRepository, Mockito.times(2)).updateFirestation(firestationTest.getAddress(),firestationTest);
	}
	
	@Test
	void TestDeleteFirestationIfExists()
	{
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		firestationService.deleteFirestation(firestationTest);
		
		assertTrue(firestationListTest.remove(firestationTest));
		verify(firestationRepository, Mockito.times(1)).deleteFirestation(firestationTest);
	}
	
	@Test
	void TestDeleteFirestationIfNotFound()
	{
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);	
		
		assertThrows(NotFoundException.class, () -> firestationService.deleteFirestation(firestationTest));
	}
}