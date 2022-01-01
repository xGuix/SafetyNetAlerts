package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest
{	
    @InjectMocks
    PersonService personService;
    
    @Mock
    PersonRepository personRepository;
    
	List<Person> personListTest;
	Person personTest;
	Person personTestForFirstName;
	Person personTestForLastName;
	
	@BeforeEach
	void setupTest()
	{
		personListTest = new ArrayList<>();
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
		personTestForFirstName = new Person("TestFirstName","TestLastName",null,null,null,null,null);
		personTestForLastName = new Person("Guix","TestLastName",null,null,null,null,null);
	}
	
	@Test
	void TestIfGetAllPersonReturnPersonList()
	{
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		List<Person> newPersonListTest = personService.getAllPersons();
		assertEquals(newPersonListTest, personListTest);
		verify(personRepository, Mockito.times(1)).getAllPerson();
	}
	
	@Test
	void TestGetPersonWithNameWhenListContainsOnePerson()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		Person personToTest = personService.getPersonByName("Guix","DeBrens");
		assertEquals(personTest, personToTest);
	}
	
	@Test
	void TestGetPersonWithNameWhenListIsEmpty()
	{
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertThrows(NotFoundException.class, () -> personService.getPersonByName("Guix","DeBrens"));
	}
	
	@Test
	void TestGetPersonWithNameWhenListContainsOnePersonButLastNameisNotGood()
	{
		personListTest.add(personTestForLastName);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertThrows(NotFoundException.class, () -> personService.getPersonByName("Guix","DeBrens"));
	}
	
	@Test
	void TestGetPersonWithNameWhenListContainsOnePersonButFistNameAndLastNameisNotGood()
	{
		personListTest.add(personTestForLastName);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertThrows(NotFoundException.class, () -> personService.getPersonByName("bernard","DeBrens"));
	}
	
	@Test
	void TestAddPersonWhenDoesNotExists()
	{
		when(personRepository.addPerson(personTest)).thenReturn(personTest);
		
		Person personToAddTest = personService.addPerson(personTest);		
		assertEquals(personToAddTest, personRepository.addPerson(personTest));
		verify(personRepository, Mockito.times(2)).addPerson(personTest);
	}
	
	@Test
	void TestAddPersonWhenAlreadyExists()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertThrows(AlreadyExistingException.class, () -> personService.addPerson(personTest));
	}
	
	@Test
	void TestAddPersonWhenWrongFirstName()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertDoesNotThrow(() -> personService.addPerson(personTestForFirstName));
	}
	
	@Test
	void TestAddPersonWhenWrongLastName()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertDoesNotThrow(() -> personService.addPerson(personTestForLastName));
	}
	
	@Test
	void TestIfUpdatePersonSendPerson()
	{
		when(personRepository.updatePerson(personTest)).thenReturn(personTest);
		
		Person personToUpdateTest = personService.updatePerson(personTest);
		assertEquals(personToUpdateTest, personRepository.updatePerson(personTest));
		verify(personRepository, Mockito.times(2)).updatePerson(personTest);
	}
	
	@Test
	void TestDeletePersonIfExists()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		personService.deletePerson(personTest);
		
		assertTrue(personListTest.remove(personTest));
		verify(personRepository, Mockito.times(1)).deletePerson(personTest);
	}
	
	@Test
	void TestDeletePersonIfWrongFirstName()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);	
		
		assertThrows(NotFoundException.class, () -> personService.deletePerson(personTestForFirstName));
	}
	
	@Test
	void TestDeletePersonIfWrongLastName()
	{
		personListTest.add(personTest);
		when(personRepository.getAllPerson()).thenReturn(personListTest);	
		
		assertThrows(NotFoundException.class, () -> personService.deletePerson(personTestForLastName));
	}
}