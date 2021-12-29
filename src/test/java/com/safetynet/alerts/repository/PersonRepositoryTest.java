package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Person;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest
{
	@InjectMocks
    PersonRepository personRepository;
	
	List<Person> personListTest;
	
	Person personTest;
	Person personTestToUD;
	Person personTestForFilterFirstName;
	Person personTestForDoesNotExists;
	Person personTestNull;

	@BeforeEach
	void setupTest()
	{
		personListTest = new ArrayList<>();
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
		personTestToUD = new Person("Guix","DeBrens","666 route du diable","L'Enfer,", "666666","0666666666","666@welcometohell.com");
		personTestForFilterFirstName = new Person("Edouard","DeBrens",null,null,null,null,null);
		personTestForDoesNotExists = new Person("Guix","Zebuth",null,null,null,null,null);
		personTestNull = new Person(null,null,null,null,null,null,null);
	}
	
	@Test
	void TestIfGetAllPersonReturnPersonList()
	{
		List<Person> newPersonListTest = personRepository.getAllPerson();
		
		assertEquals(newPersonListTest, personListTest);
	}
	
	@Test
	void TestIfAddPersonReturnUpdateList()
	{
		personListTest.add(0,personTest);
		Person newPersonAddTest = personRepository.addPerson(personTest);

		assertEquals(newPersonAddTest, personTest);
	}
	
	@Test
	void TestUpdatePersonWhenListContainsThePerson()
	{
		personListTest.add(0,personTest);
		Person newPersonUpdateTest = personListTest.set(personListTest.indexOf(personTest),personTest);
		
		assertEquals(newPersonUpdateTest,personTest);
	}	

	@Test
	void TestIfUpdatePersonWhenPersonIsNull()
	{
		personListTest.add(0,personTestNull);
		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTest));
	}
	
	@Test
	void TestIfUpdatePersonWhenPersonIsNotGood()
	{
		personListTest.add(0,personTestForDoesNotExists);
		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTest));
	}
	
	@Test
	void TestIfUpdatePersonWhenPersonFirstNameNotGood()
	{
		personListTest.add(0,personTestForFilterFirstName);
		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTest));
	}
	
	@Test
	void TestDeletePersonRemovePersonFromList()
	{
		personListTest.add(0,personTest);
		personRepository.deletePerson(personTest);
		
		assertTrue(personListTest.remove(personTest));
	}
}