package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
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
	Person personTestToUD;
	Person personTestForFirstName;
	Person personTestForLastName;

	@BeforeAll
	public static void setupTestForAll()
	{
		personListTest = new ArrayList<>(Arrays.asList(
			new Person("John", "Boyd", "1509 Culver St", "Culver", "97451","841-874-6512", "jaboyd@email.com"),
			new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
			new Person("Tenley","Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"),
			new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
			new Person("Felicia", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com"),
			new Person("Jonanathan", "Marrack", "29 15th St", "Culver", "97451", "841-874-6513", "drk@email.com"),
			new Person("Tessa", "Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com"),
			new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
			new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com"),
			new Person("Tony", "Cooper", "112 Steppes Pl", "Culver", "97451", "841-874-6874", "tcoop@ymail.com"),
			new Person("Lily", "Cooper", "489 Manchester St", "Culver", "97451", "841-874-9845", "lily@email.com"),
			new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878", "soph@email.com"),
			new Person("Warren", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7512", "ward@email.com"),
			new Person("Zach", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7512", "zarc@email.com"),
			new Person("Reginold", "Walker", "908 73rd St", "Culver", "97451", "841-874-8547", "reg@email.com"),
			new Person("Jamie", "Peters", "908 73rd St", "Culver", "97451", "841-874-7462", "jpeter@email.com"),
			new Person("Ron", "Peters", "112 Steppes Pl", "Culver", "97451", "841-874-8888", "jpeter@email.com"),
			new Person("Allison", "Boyd", "112 Steppes Pl", "Culver", "97451", "841-874-9888", "aly@imail.com"),
			new Person("Brian", "Stelzer","947 E. Rose Dr","Culver", "97451", "841-874-7784", "bstel@email.com"),
			new Person("Shawna", "Stelzer", "947 E. Rose Dr","Culver","97451","841-874-7784","ssanw@email.com"),
			new Person("Kendrik","Stelzer", "947 E. Rose Dr", "Culver", "97451","841-874-7784","bstel@email.com"),
			new Person("Clive", "Ferguson","748 Townings Dr", "Culver", "97451","841-874-6741", "clivfd@ymail.com"),
			new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com")
		));
	}
	
	@BeforeEach
	void setupTestForEach()
	{
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
		personTestToUD = new Person("Guix","DeBrens","666 route du diable","L'Enfer,", "666666","0666666666","666@welcometohell.com");
		personTestForFirstName = new Person("Edouard","DeBrens",null,null,null,null,null);
		personTestForLastName = new Person("Guix","Zebuth",null,null,null,null,null);
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