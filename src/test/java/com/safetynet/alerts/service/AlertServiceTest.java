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

import com.safetynet.alerts.dto.ChildAlertDto;
import com.safetynet.alerts.dto.FireAlertDto;
import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.dto.FirestationPersonsDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
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
    
	Person personTest;
	Person person2Test;
	List<Person> personListTest;
	List<MedicalRecord> medicalRecordListTest;
	
	MedicalRecord medicalRecordTest;
	List<String> medicationUD;
	List<String> allergieUD;
	
	MedicalRecord medicalRecord2Test;
	List<String> medication2UD;
	List<String> allergie2UD;
	
	FirestationPersonsDto personFirestationTest;
	FirestationPersonsDto personFirestation2Test;
	List<FirestationPersonsDto> firestationPersonsListDto;	
	
	ChildAlertDto childAlertTest;
	List<ChildAlertDto> childAlertTestListDto;
	
	Firestation firestationTest;
	FireAlertDto fireAlertDto;
	PersonInfoDto personInfoTest;
	List<PersonInfoDto> personInfoListDto;
	
	List<String> addresses;
	Set<String> emails;
	Set<String> phone;
	
	@BeforeEach
	void setupTest()
	{
		personListTest = new ArrayList<>();
		medicalRecordListTest = new ArrayList<>();
		firestationPersonsListDto = new ArrayList<>();
		childAlertTestListDto = new ArrayList<>();
		personInfoListDto = new ArrayList<>();

		personTest = new Person("Guix","DeBrens","150 rue Houdan","Sceaux", "92330","0630031876","guix92@hotmail.com");
		personFirestationTest = new FirestationPersonsDto("Guix","DeBrens","150 rue Houdan","0630031876");
		medicationUD = Arrays.asList("Alcohol: 2 bottles of Whisky","Tabacco: 5 packets","Doner-kebbab: 3 times a day");
		allergieUD = Arrays.asList("Liar","Storyteller");
		medicalRecordTest = new MedicalRecord("Guix","DeBrens", "22/10/1982",medicationUD,allergieUD);
		
		person2Test = new Person("Bel","Zebuth","666 Devil Street","Sceaux", "666999","0666669999","666@welcomToHell.com");
		personFirestation2Test = new FirestationPersonsDto("Bel","Zebuth","666 Devil Street","0666669999");
		medication2UD = Arrays.asList("Lexomil: 120mg","Oxycodone: 600mg","Darkmagic: hallellouia");
		allergie2UD = Arrays.asList("God","Heaven");
		medicalRecord2Test = new MedicalRecord("Bel","Zebuth", "1/01/2021",medication2UD,allergie2UD);
		
		firestationTest = new Firestation("150 rue Houdan", "1");
		personInfoTest = new PersonInfoDto("Guix","DeBrens",39,"0630031876","not disclosed", medicationUD,allergieUD);
		
		addresses = Arrays.asList("150 rue Houdan","666 Devil Street");
		emails = Stream.of("guix92@hotmail.com","666@welcomToHell.com").collect(Collectors.toSet());
		phone = Stream.of("0630031876","0666669999").collect(Collectors.toSet());

	}
	
	@Test
	void TestIfGetPersonListWithChildrenNumberForStationReturnDto()
	{   
		personListTest.add(personTest);
		personListTest.add(person2Test);
		firestationPersonsListDto.add(personFirestationTest);
		firestationPersonsListDto.add(personFirestation2Test);
		FirestationPersonAlertDto expected = new FirestationPersonAlertDto(firestationPersonsListDto,1,1);
		
		when(firestationService.getOnlyAddressesFor("1")).thenReturn(addresses);
		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		when(medicalRecordService.getHowOld("Bel", "Zebuth")).thenReturn(1);
		
		FirestationPersonAlertDto fpaForTest;
		fpaForTest = alertService.getPersonsListWithChildrenNumberForStation("1");
		
		verify(firestationService, Mockito.times(1)).getOnlyAddressesFor("1");
		verify(personService, Mockito.times(1)).getAllPersons();
		verify(medicalRecordService, Mockito.times(2)).getHowOld("Guix", "DeBrens");
		verify(medicalRecordService, Mockito.times(1)).getHowOld("Bel", "Zebuth");
		
		assertEquals(expected, fpaForTest);
	}
	
	@Test
	void TestIfgetChildrenWithFamilyListAtAddressReturnListDto()
	{   
		personListTest.add(personTest);
		personListTest.add( new Person("Bel","Zebuth","150 rue Houdan","Sceaux", "666999","0666669999","666@welcomToHell.com"));
		firestationPersonsListDto.add(personFirestationTest);
		childAlertTest = new ChildAlertDto("Bel","Zebuth", 1, firestationPersonsListDto); 
		childAlertTestListDto.add(childAlertTest);
		List<ChildAlertDto> expected = childAlertTestListDto;

		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Bel", "Zebuth")).thenReturn(1);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		
		List<ChildAlertDto> childAlertForTest;
		childAlertForTest = alertService.getChildrenWithFamilyListAtAddress("150 rue Houdan");
		
		verify(personService, Mockito.times(1)).getAllPersons();
		verify(medicalRecordService, Mockito.times(2)).getHowOld("Bel", "Zebuth");
		verify(medicalRecordService, Mockito.times(1)).getHowOld("Guix", "DeBrens");
	
		assertEquals(expected, childAlertForTest);
	}
	
	@Test
	void TestGetPhoneNumberOfStationReturnPhoneList()
	{
		personListTest.add(personTest);
		personListTest.add(person2Test);
		when(firestationService.getOnlyAddressesFor("1")).thenReturn(addresses);
		when(personService.getAllPersons()).thenReturn(personListTest);
		
		Set<String> phoneNumberTest;
		phoneNumberTest = alertService.getPhoneNumberOfStation("1");
		
		verify(firestationService, Mockito.times(1)).getOnlyAddressesFor("1");
		verify(personService, Mockito.times(1)).getAllPersons();
		assertEquals(phone, phoneNumberTest);
	}
	
	@Test
	void TestGetPersonsListAndFirestationForAddressReturnList()
	{
		personListTest.add(personTest);
		personInfoListDto.add(personInfoTest);
		FireAlertDto expected = new FireAlertDto(firestationTest,personInfoListDto);
		when(firestationService.getOneFirestation("150 rue Houdan")).thenReturn(firestationTest);
		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		when(medicalRecordService.getMedicalRecordByName("Guix", "DeBrens")).thenReturn(medicalRecordTest);
		
		FireAlertDto PersonsListTest;
		PersonsListTest = alertService.getPersonsListAndFirestationForAddress("150 rue Houdan");
		
		verify(firestationService, Mockito.times(1)).getOneFirestation("150 rue Houdan");
		verify(personService, Mockito.times(1)).getAllPersons();
		verify(medicalRecordService, Mockito.times(1)).getHowOld("Guix", "DeBrens");
		verify(medicalRecordService, Mockito.times(2)).getMedicalRecordByName("Guix", "DeBrens");
		assertEquals(expected, PersonsListTest);
	}
	
	@Test
	void TestGetEmailsListByCityReturnList()
	{
		personListTest.add(personTest);
		personListTest.add(person2Test);
		when(personService.getAllPersons()).thenReturn(personListTest);
		
		Set<String> testList = alertService.getEmailsListByCity("Sceaux");
		
		verify(personService, Mockito.times(1)).getAllPersons();
		assertEquals(testList, emails);
	}
}