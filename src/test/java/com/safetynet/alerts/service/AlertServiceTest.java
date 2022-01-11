package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.dto.FirestationPersonsDto;
import com.safetynet.alerts.model.Firestation;
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
    
	List<String> addresses;
	Set<String> emails;
	List<FirestationPersonsDto> firestationPersonsDto;
	List<Person> personListTest;
	
	FirestationPersonAlertDto firestationPersonAlertTest;
	FirestationPersonsDto personWithAgeTest;	
	Firestation firestationTest;
	Person personTest;
	Person personTest2;
	
	@BeforeEach
	void setupTest()
	{
		addresses = Arrays.asList("150 Rue Houdan");
		emails = Stream.of("guix92@hotmail.com","666@welcomToHell.com").collect(Collectors.toSet());
		firestationPersonsDto = new ArrayList<>();
		personListTest = new ArrayList<>();
		personWithAgeTest = new FirestationPersonsDto("Guix","DeBrens","150 Rue Houdan","0630031876");
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux", "92330","0630031876","guix92@hotmail.com");
		personTest2 = new Person("Bel","zebuth","666 Devil Street","Sceaux", "666999","0666669999","666@welcomToHell.com");
	}
	
	@Test
	void TestIfGetPersonListWithChildrenNumberForStationReturnList()
	{   
        int childrenNumber = 0;
        int adultsNumber = 0;
        List<FirestationPersonsDto> personListAtAddress = new ArrayList<>();
        FirestationPersonAlertDto firestationPersonForTest= new FirestationPersonAlertDto(personListAtAddress, adultsNumber, childrenNumber);
		when(firestationService.getOnlyAddressesFor("1")).thenReturn(addresses);
		
		FirestationPersonAlertDto firestationPersonAlertTest;
		firestationPersonAlertTest = alertService.getPersonsListWithChildrenNumberForStation("1");
		
		assertEquals(firestationPersonAlertTest, firestationPersonForTest );
	}
	
	
	@Test
	void TestGetEmailsListByCityReturnList()
	{
		personListTest.add(personTest);
		personListTest.add(personTest2);
		when(personService.getAllPersons()).thenReturn(personListTest);
		
		Set<String> testList = alertService.getEmailsListByCity("Sceaux");
		
		verify(personService, Mockito.times(1)).getAllPersons();
		assertEquals(testList, emails);
	}
}