package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonsController {
	
	private static Logger logger = LogManager.getLogger("PersonController");
	
	@Autowired
	private IPersonService personService;
	
	/**
	 * Set Person list for integrationTest
	 * 
	 * @param - {personService}
	 */
	public void setPersonsService(PersonService personService)
	{
		this.personService = personService;
	}

	/**
	 * Read list :
	 * Get all persons
	 * 
	 * @return - Full persons list
	 */
	@GetMapping(value = "/persons")
	public  ResponseEntity<List<Person>> getAllPersons()
	{
		logger.info("Sending request for persons list...");	
		return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.FOUND);
	}
	
	/**
	 * Read person :
	 * Get a person by name
	 * 
	 * @param {firstName} & {lastName}
	 * @return - The person data
	 */
    @GetMapping(value ="/person")
    public  ResponseEntity<Person> getPersonByName(@RequestParam String firstName, @RequestParam String lastName)
    {
		logger.info("Sending request to find person :'{} {}'", firstName,lastName);
        return new ResponseEntity<>(personService.getPersonByName(firstName,lastName), HttpStatus.FOUND);
    }
	
	/**
	 * Create person :
	 * Add a new person
	 * 
	 * @param - {Person} - Model as object
	 * @return - Person added
	 */
	@PostMapping(value ="/person")
	public ResponseEntity<Person> addNewPerson(@RequestBody Person person)
	{
		logger.info("Sending request to add : {}", person);
		return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
	}
    
	/**
	 * Update Person :
	 * Modif info of a person by name
	 * 
	 * @param - {Person} Body
	 * @return - Update new data to person
	 */
    @PutMapping(value = "/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person)
    {
		logger.info("Sending Person to update : {}", person);
		return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
    }
	
	/**
	 * Delete Person :
	 * Person to delete
	 * 
	 * @param - {firstName} & {lastName} - Person to delete
	 */
	@DeleteMapping(value = "/person")
	public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName)
	{
		logger.info("Sending Person to delete :'{} {}’", firstName,lastName);
		personService.deletePerson(personService.getPersonByName(firstName, lastName));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}