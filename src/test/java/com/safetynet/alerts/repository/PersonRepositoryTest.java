package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.model.Person;

@SpringBootTest
class PersonRepositoryTest
{
    @Autowired
    PersonRepository personRepository;
    
	List<Person> personListTest;
	Person personTest;
	
	@BeforeEach
	void setupTest() {
		personTest = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux,", "92330","0630031876","guix92@hotmail.com");
	}
	
	@Test
	void TestIfGetAllPersonReturnPersonList() {
			
		when(personRepository.getAllPerson()).thenReturn(personListTest);
		personListTest.add(0,personTest);
		assertThat(personListTest.contains(personTest)).isTrue();
	}
}