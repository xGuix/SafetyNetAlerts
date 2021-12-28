package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
	Person personTestForFilter;
	
	@BeforeEach
	void setupTest()
	{
		personListTest = new ArrayList<>();
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
		personTestForFilter = new Person("Guix","TestLastName",null,null,null,null,null);
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
		personListTest.add(personTestForFilter);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertThrows(NotFoundException.class, () -> personService.getPersonByName("Guix","DeBrens"));
	}
	
	@Test
	void TestGetPersonWithNameWhenListContainsOnePersonButFistNameAndLastNameisNotGood()
	{
		personListTest.add(personTestForFilter);
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		
		assertThrows(NotFoundException.class, () -> personService.getPersonByName("bernard","DeBrens"));
	}
	
	@Test
	void TestIfAddPersonSendPerson()
	{
		when(personRepository.addPerson(personTest)).thenReturn(personTest);
		
		assertEquals(personService.addPerson(personTest), personRepository.addPerson(personTest));
		verify(personRepository, Mockito.times(2)).addPerson(personTest);
	}
	
	@Test
	void TestIfUpdatePersonSendPerson()
	{
		when(personRepository.updatePerson(personTest)).thenReturn(personTest);
		
		assertEquals(personService.updatePerson(personTest), personRepository.updatePerson(personTest));
		verify(personRepository, Mockito.times(2)).updatePerson(personTest);
	}
	
	@Test
	void TestDeletePersonIsCall()
	{
		personService.deletePerson(personTest);
		
		verify(personRepository, Mockito.times(1)).deletePerson(personTest);
	}
}