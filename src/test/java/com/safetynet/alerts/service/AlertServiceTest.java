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
import com.safetynet.alerts.dto.FloodAlertDto;
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
	
	FloodAlertDto floodAlertDto;
	FloodAlertDto floodAlert2Dto;
	List<FloodAlertDto> floodAlertTestListDto;
	
	Firestation firestationTest;
	Firestation firestation2Test;
	FireAlertDto fireAlertDto;
	
	PersonInfoDto personListAndFirestationTest;
	PersonInfoDto personHomeFamilyTest;
	PersonInfoDto personHomeFamily2Test;
	PersonInfoDto personInfoDto;
	List<PersonInfoDto> personInfoListDto;
	List<PersonInfoDto> personInfo2ListDto;
	
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
		floodAlertTestListDto = new ArrayList<>();
		personInfoListDto = new ArrayList<>();
		personInfo2ListDto = new ArrayList<>();

		personTest = new Person("Guix","DeBrens","777 Paradise Road","Heaven", "92330","0630031876","guix92@hotmail.com");
		personFirestationTest = new FirestationPersonsDto("Guix","DeBrens","777 Paradise Road","0630031876");
		medicationUD = Arrays.asList("Alcohol: 2 bottles of Whisky","Tabacco: 5 packets","Doner-kebbab: 3 times a day");
		allergieUD = Arrays.asList("Liar","Storyteller");
		medicalRecordTest = new MedicalRecord("Guix","DeBrens", "22/10/1982",medicationUD,allergieUD);
		
		person2Test = new Person("Bel","Zebuth","666 Devil Street","Heaven", "666999","0666669999","666@welcomToHell.com");
		personFirestation2Test = new FirestationPersonsDto("Bel","Zebuth","666 Devil Street","0666669999");
		medication2UD = Arrays.asList("Lexomil: 120mg","Oxycodone: 600mg","Darkmagic: hallellouia");
		allergie2UD = Arrays.asList("God","Heaven");
		medicalRecord2Test = new MedicalRecord("Bel","Zebuth", "1/01/2021",medication2UD,allergie2UD);
		
		firestationTest = new Firestation("777 Paradise Road", "1");
		firestation2Test = new Firestation("666 Devil Street", "1");
		personListAndFirestationTest = new PersonInfoDto("Guix","DeBrens",39,"0630031876","not disclosed", medicationUD,allergieUD);
		personHomeFamilyTest = new PersonInfoDto("Guix","DeBrens",39,"not disclosed","guix92@hotmail.com", medicationUD,allergieUD);
		personHomeFamily2Test= new PersonInfoDto("Bel","Zebuth",1,"not disclosed","666@welcomToHell.com",medication2UD,allergie2UD);
		personInfoDto = new PersonInfoDto("Guix","DeBrens",39,"0630031876","guix92@hotmail.com", medicationUD, allergieUD);
		addresses = Arrays.asList("777 Paradise Road","666 Devil Street");
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
		personListTest.add( new Person("Bel","Zebuth","777 Paradise Road","Heaven", "666999","0666669999","666@welcomToHell.com"));
		firestationPersonsListDto.add(personFirestationTest);
		childAlertTest = new ChildAlertDto("Bel","Zebuth", 1, firestationPersonsListDto); 
		childAlertTestListDto.add(childAlertTest);
		List<ChildAlertDto> expected = childAlertTestListDto;

		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Bel", "Zebuth")).thenReturn(1);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		
		List<ChildAlertDto> childAlertForTest;
		childAlertForTest = alertService.getChildrenWithFamilyListAtAddress("777 Paradise Road");
		
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
		personInfoListDto.add(personListAndFirestationTest);
		FireAlertDto expected = new FireAlertDto(firestationTest,personInfoListDto);
		when(firestationService.getOneFirestation("777 Paradise Road")).thenReturn(firestationTest);
		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		when(medicalRecordService.getMedicalRecordByName("Guix", "DeBrens")).thenReturn(medicalRecordTest);
		
		FireAlertDto PersonsListTest;
		PersonsListTest = alertService.getPersonsListAndFirestationForAddress("777 Paradise Road");
		
		verify(firestationService, Mockito.times(1)).getOneFirestation("777 Paradise Road");
		verify(personService, Mockito.times(1)).getAllPersons();
		verify(medicalRecordService, Mockito.times(1)).getHowOld("Guix", "DeBrens");
		verify(medicalRecordService, Mockito.times(2)).getMedicalRecordByName("Guix", "DeBrens");
		assertEquals(expected, PersonsListTest);
	}
	
	@Test
	void TestGetHomeFamilyforStation()
	{
		personListTest.add(personTest);
		personListTest.add(person2Test);
		personInfoListDto.add(personHomeFamilyTest);
		personInfo2ListDto.add(personHomeFamily2Test);
		floodAlertDto = new FloodAlertDto(firestationTest.getAddress(),personInfoListDto);
		floodAlert2Dto = new FloodAlertDto(firestation2Test.getAddress(),personInfo2ListDto);
		floodAlertTestListDto.add(floodAlertDto);
		floodAlertTestListDto.add(floodAlert2Dto);
		List<FloodAlertDto> expected = floodAlertTestListDto;
		
		when(firestationService.getOnlyAddressesFor("1")).thenReturn(addresses);
		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		when(medicalRecordService.getHowOld("Bel", "Zebuth")).thenReturn(1);
		when(medicalRecordService.getMedicalRecordByName("Guix", "DeBrens")).thenReturn(medicalRecordTest);
		when(medicalRecordService.getMedicalRecordByName("Bel", "Zebuth")).thenReturn(medicalRecord2Test);

		List<FloodAlertDto> floodAlertTotest = alertService.getHomeFamilyforStation("1");
		
		verify(firestationService, Mockito.times(1)).getOnlyAddressesFor("1");
		verify(personService, Mockito.times(2)).getAllPersons();
		verify(medicalRecordService, Mockito.times(2)).getHowOld("Guix", "DeBrens");
		verify(medicalRecordService, Mockito.times(4)).getMedicalRecordByName("Guix", "DeBrens");
		assertEquals(expected, floodAlertTotest);
	}
	
	@Test
	void TestGetPersonInfo()
	{
		personListTest.add(personTest);
		personInfoListDto.add(personInfoDto);
		List<PersonInfoDto> expected = personInfoListDto;
		when(personService.getAllPersons()).thenReturn(personListTest);
		when(medicalRecordService.getHowOld("Guix", "DeBrens")).thenReturn(39);
		when(medicalRecordService.getMedicalRecordByName("Guix", "DeBrens")).thenReturn(medicalRecordTest);
		
		List<PersonInfoDto> personInfoTotest = alertService.getPersonInfo("Guix", "DeBrens");
		
		verify(personService, Mockito.times(1)).getAllPersons();
		verify(medicalRecordService, Mockito.times(1)).getHowOld("Guix", "DeBrens");
		verify(medicalRecordService, Mockito.times(2)).getMedicalRecordByName("Guix", "DeBrens");
		assertEquals(expected, personInfoTotest);
	}
	
	@Test
	void TestGetEmailsListByCityReturnList()
	{
		personListTest.add(personTest);
		personListTest.add(person2Test);
		when(personService.getAllPersons()).thenReturn(personListTest);
		
		Set<String> testList = alertService.getEmailsListByCity("Heaven");
		
		verify(personService, Mockito.times(1)).getAllPersons();
		assertEquals(testList, emails);
	}
}