package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Person;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest
{
	@InjectMocks
    PersonRepository personRepository;
	
	@Mock
	List<Person> personListTest = new ArrayList<>();
	
	Person personTest;
	Person personTestToUD;
	Person personTestForFilter;
	Person personTestForDoesNotExists;

	@BeforeEach
	void setupTest()
	{
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
		personTestToUD = new Person("Guix","DeBrens","666 route du diable","L'Enfer,", "666666","0666666666","666@welcometohell.com");
		personTestForFilter = new Person(null,null,null,null,null,null,null);
		personTestForDoesNotExists = new Person("Bel","Zebuth",null,null,null,null,null);
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
		personRepository.addPerson(personTest);
		List<Person> newPersonListAddTest = personRepository.getAllPerson();
		assertEquals(newPersonListAddTest, personListTest);
	}
	
//***************************************************************************************	

	@Test
	void TestUpdatePersonWhenListContainsThePerson()
	{
		when(personListTest.get(0)).thenReturn(personTest);
		Person newToto = personListTest.get(0);
		
		assertEquals(newToto,personTest);
	}	

	@Test
	void TestIfUpdatePersonWhenPersonIsNull()
	{
		when(personListTest.equals(personRepository.getAllPerson())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTestForDoesNotExists));
	}
	
//***************************************************************************************	
	
	@Test
	void TestDeletePersonRemovePersonFromList()
	{
		personRepository.deletePerson(personTest);		
		verify(personListTest,times(1)).remove(personTest);
	}
}