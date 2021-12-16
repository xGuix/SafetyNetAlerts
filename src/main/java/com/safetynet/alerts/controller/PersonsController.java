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

@RestController
public class PersonsController {
	
	private static Logger logger = LogManager.getLogger("PersonController");
	
	@Autowired
	private IPersonService personService;
	
	/**
	 * Read - Get all persons
	 * @return - Full persons list
	 */
	@GetMapping(value = "/persons")
	public  ResponseEntity<List<Person>> getAllPersons()
	{
		logger.info("Show persons list");
		return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.FOUND);
	}
	
	/**
	 * Read - Get a person by name
	 * @param {firstName} & {lastName}
	 * @return - The person data
	 */
    @GetMapping(value ="/person")
    public  ResponseEntity<Person> getPersonByName(@RequestParam String firstName, @RequestParam String lastName)
    {
		logger.info("Search for person ; {} {}", firstName,lastName);
        return new ResponseEntity<> (personService.getPersonByName(firstName,lastName), HttpStatus.FOUND);
    }
	
	/**
	 * Create - Add a new person
	 * @param Person - Model as object
	 * @return Person added
	 */
	@PostMapping(value ="/person")
	public ResponseEntity<Person> addNewPerson(@RequestBody Person person)
	{
		logger.info("Person to add in persons list : {}", person);
		return new ResponseEntity<> (personService.addPerson(person),HttpStatus.CREATED);
	}
    
	/**
	 * Update Person - Modif info of a person by name
	 * @param Person Body
	 * @return - Update new data to person
	 */
    @PutMapping(value = "/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person)
    {
		logger.info("Person info to update : {}", person);
        return new ResponseEntity<> (personService.updatePerson(person),HttpStatus.OK);
    }
	
	/**
	 * Delete - Person to delete
	 * @param {firstName} & {lastName} - Person to delete
	 */
	@DeleteMapping(value = "/person")
	public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName)
	{
		logger.info("Person to delete from the list : {} {}", firstName, lastName);
		personService.deletePerson(personService.getPersonByName(firstName, lastName));
		return new ResponseEntity<> (HttpStatus.OK);
	}
}