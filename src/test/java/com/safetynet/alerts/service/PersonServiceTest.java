package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@SpringBootTest
class PersonServiceTest
{	
    @Autowired
    PersonService personService;
    
    @MockBean
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
		assertThat(personService.getAllPersons()).isEqualTo(personListTest);
		verify(personRepository, Mockito.times(1)).getAllPerson();
	}
	
	@Test
	void TestGetPersonWithName() {
		
		//Stream ps = mock(Stream.class);
		//when(personService.getAllPersons().stream()).thenReturn(ps);
		
		assertThat(personService.getPersonByName("Guix","DeBrens")).isEqualTo(personTest);
		verify(personService, Mockito.times(1)).getPersonByName("Guix","DeBrens");
	}
}