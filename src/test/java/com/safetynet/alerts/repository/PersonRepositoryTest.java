package com.safetynet.alerts.repository;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.model.Person;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest
{
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
	/*********************************
	@Test
	void TestIfGetAllPersonReturnPersonList()
	{
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		List<Person> newPersonListTest = personRepository.getAllPerson();
		
		assertEquals(newPersonListTest, personListTest);
		verify(personRepository, Mockito.times(1)).getAllPerson();
	}
	***********************************/
	
	@Test
	void TestDeletePersonIsCall()
	{
		personListTest.add(personTest);
		personRepository.deletePerson(personTest);
		verify(personRepository, Mockito.atLeastOnce()).deletePerson(personTest);;
	}
}