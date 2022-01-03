package com.safetynet.alerts.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

class PersonIntegrationTest
{
    PersonsController personsController= new PersonsController();
    PersonService personService = new PersonService();
    PersonRepository personRepository = new PersonRepository();
    Person person;
    
    @BeforeEach
    public void setupTest()
    {
    	personRepository.setPersonList(personRepository.getPersonList());
    	personService.setPersonRepository(personRepository);
    	personsController.setPersonsService(personService);
    	
        person = new Person("Guix","DeBrens","150 Rue Houdan","Sceaux", "92330","0630031876","guix92@hotmail.com");
    	personRepository.addPerson(person);
    }
    
    @Test
    void getAllPersonsTest()
    {
    	List<Person> expected = personRepository.getAllPerson();
    	List<Person> result;
    	
    	result = personsController.getAllPersons().getBody();

        assertTrue(personRepository.getAllPerson().contains(person));
        assertEquals(expected, result);
    }

    @Test
    void getPersonWithFistNameAndLastNameTest()
    {
        Person expected = personService.getPersonByName("Guix","DeBrens");
        Person result;
        
        result = personsController.getPersonByName("Guix","DeBrens").getBody();

        assertTrue(personRepository.getAllPerson().contains(person));
        assertEquals(expected, result);
    }
    
    @Test
   void addPersonTest()
    {
        Person personToAdd =  new Person("Bel","Zebuth","666 Demons Road","Hell", "999666","0666666666","666@welcomeToHell.com");
        List <Person> expected = personRepository.getAllPerson();
        
        personsController.addNewPerson(personToAdd);
        
        List <Person> result = personRepository.getPersonList();
        assertTrue(personRepository.getAllPerson().contains(personToAdd));
        assertEquals(expected, result);
    }

    @Test
    void updatePersonTest()
    {
        Person personToEdit = personRepository.updatePerson(person);
        List<Person> expected = personRepository.getAllPerson();

        personsController.updatePerson(personToEdit);
        
        List<Person> result = personRepository.getPersonList();
        assertTrue(personRepository.getAllPerson().contains(personToEdit));
        assertEquals(expected, result);
    }

    @Test
    void deletePersonTest()
    {
        List<Person> expected = personRepository.getAllPerson();

        personsController.deletePerson("Guix","DeBrens");

        List<Person> result = personRepository.getPersonList();
        assertFalse(personRepository.getAllPerson().contains(person));
        assertEquals(expected, result);
    }   
}