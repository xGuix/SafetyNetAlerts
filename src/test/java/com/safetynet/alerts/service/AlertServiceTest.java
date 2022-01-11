package com.safetynet.alerts.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.dto.PersonWithAgeDto;
import com.safetynet.alerts.model.Person;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest
{	
    @InjectMocks
    AlertService alertService;
    
    @Mock
    PersonService personService;
    
    @Mock
    FirestationService firestationService;
    
    @Mock
    MedicalRecordService medicalRecordService;
    
	List<String> emails;
	List<PersonWithAgeDto> personWithAgeDto;
	List<Person> personListTest;
	
	FirestationPersonAlertDto firestationPersonAlertTest;
	PersonWithAgeDto personWithAgeTest;
	Person personTest;
	Person personTest2;
	Person personTestForFirstName;
	Person personTestForLastName;
	
	@BeforeEach
	void setupTest()
	{		
		emails = Arrays.asList("guix92@hotmail.com","666@welcomToHell.com");
		personWithAgeDto = new ArrayList<>();
		personListTest = new ArrayList<>();
		personWithAgeTest = new PersonWithAgeDto("Guix","DeBrens","150 Rue Houdan","0666669999", 99);
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux", "92330","0630031876","guix92@hotmail.com");
		personTest2 = new Person("Bel","zebuth","666 Devil Street","Sceaux", "666999","0630031876","666@welcomToHell.com");
		personTestForFirstName = new Person("TestFirstName","TestLastName",null,null,null,null,null);
		personTestForLastName = new Person("Guix","TestLastName",null,null,null,null,null);
	}
	
	@Test
	void TestIfGetPersonListWithChildrenNumberForStationReturnList()
	{   
        int childrenNumber = 0;
        int adultsNumber = 0;
        List<PersonWithAgeDto> personListAtAddress = new ArrayList<>();
        FirestationPersonAlertDto firestationPersonForTest= new FirestationPersonAlertDto(personListAtAddress, adultsNumber, childrenNumber);
		when(personService.getAllPersons()).thenReturn(personListTest);
		
		FirestationPersonAlertDto firestationPersonAlertTest;
		firestationPersonAlertTest = alertService.getPersonsListWithChildrenNumberForStation("1");
		
		assertEquals(firestationPersonAlertTest, firestationPersonForTest );
	}
	
	
	@Test
	void TestGetEmailsListByCityReturnList()
	{

		when(personService.getAllPersons()).thenReturn(personListTest);
		
		List<String> testList = alertService.getEmailsListByCity("city");
		
		assertEquals(testList, personListTest);
	}
	
/*	
	@Test
	void TestIfGetPersonListWithChildrenNumberForStationReturnList()
	{
		when(alertService.getPersonsListWithChildrenNumberForStation()).thenReturn(FirestationPersonAlertDto);
		List<Firestation> allFirestationListTest = alertService.getAllFirestations();
		
		assertEquals(allFirestationListTest, firestationListTest);
		verify(firestationRepository, Mockito.times(1)).getAllFirestation();
	}

	@Test
	void TestIfGetFirestationWithStationReturnFirestationList()
	{
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		List<Firestation> firestationsListToTest = alertService.getFirestationsFor("1");
		assertEquals(firestationsListToTest, firestationListTest);
	}
	
	@Test
	void TestIfGetFirestationWithStationReturnOnlyAddressesList()
	{
		List<String> AddressesListTest = new ArrayList<>();
		AddressesListTest.add(firestationTest.getAddress());
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		List<String> firestationAddressListToTest = alertService.getOnlyAddressesFor("1");
		assertEquals(firestationAddressListToTest, AddressesListTest);
	}
	
	@Test
	void TestIfGetOneFirestationReturnFirestationWithAddressAndStation()
	{
		firestationListTest.add(firestationTest);
		when(firestationRepository.getAllFirestation()).thenReturn(firestationListTest);
		
		Firestation firestationToTest = alertService.getOneFirestation("Saint Omer Sur Mer");
		assertEquals(firestationToTest,firestationTest);
	}
*/
}