package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Person;

@SpringBootTest
class PersonRepositoryTest
{
	@Autowired
    PersonRepository personRepository;
	
	public static List<Person> personListTest;
	
	Person personTest;
	Person personTestForFirstName;
	Person personTestForLastName;

	@BeforeAll
	public static void setupTestForAll()
	{
		personListTest = new ArrayList<>();
	}
	
	@BeforeEach
	void setupTestForEach()
	{
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
		personTestForFirstName = new Person("Belzebuth","DeBrens",null,null,null,null,null);
		personTestForLastName = new Person("Guix","Belzebuth",null,null,null,null,null);
	}
	
	@Test
	void TestIfGetAllPersonReturnPersonList()
	{
		List <Person> newPersonListTest = personRepository.getAllPerson();
		
		assertEquals(newPersonListTest, personRepository.getAllPerson());

	}
	
	@Test
	void TestAddPersonReturnPerson()
	{
		Person newPersonAddTest = personRepository.addPerson(personTest);

		assertEquals(newPersonAddTest, personTest);
	}

	@Test
	void TestUpdatePersonWhenExists()
	{
		personListTest.add(personTest);
		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTestForLastName));
	}
	
	@Test
	void TestUpdatePersonWhenSomethingWrong()
	{
		personListTest.add(personTest);
		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTestForFirstName));
	}

	@Test
	void TestUpdatePersonWhenIsWrongFirstName()
	{	
		personListTest.add(personTestForFirstName);

		assertDoesNotThrow(() -> personRepository.updatePerson(personTest));
	}
	
	@Test
	void TestUpdatePersonWhenIsWrongLastName()
	{
		personListTest.add(personTestForLastName);

		assertThrows(NotFoundException.class, () -> personRepository.updatePerson(personTest));
	}
	
	
	@Test
	void TestDeletePersonRemovePersonFromList()
	{
		personListTest.add(personTest);
		personRepository.deletePerson(personTest);
		assertTrue(personListTest.remove(personTest));
	}
}