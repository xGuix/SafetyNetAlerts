package com.safetynet.alerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.controller.PersonsController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

@SpringBootTest
@AutoConfigureMockMvc
class PersonIntegrationTestIT
{
    @Autowired
    private MockMvc mockMvc;
	
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
    void getAllPersonsTestMvc() throws Exception
    {
        mockMvc.perform(get("/persons"))
        		.andExpect(status().isFound())
        		.andExpect(jsonPath("$[0].firstName",is("John")))
        		.andExpect(jsonPath("$[0].lastName",is("Boyd")))
        		.andExpect(jsonPath("$[0].address",is("1509 Culver St")))
        		.andExpect(jsonPath("$[0].city",is("Culver")))
        		.andExpect(jsonPath("$[0].zip",is("97451")))
        		.andExpect(jsonPath("$[0].phone",is("841-874-6512")))
        		.andExpect(jsonPath("$[0].email",is("jaboyd@email.com")))
       			.andReturn();
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
    void addPersonTest() throws Exception
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