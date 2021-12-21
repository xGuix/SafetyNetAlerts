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
import org.springframework.web.servlet.mvc.method.annotation.RequestAttributeMethodArgumentResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.fasterxml.jackson.databind.node.MissingNode;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

import groovyjarjarpicocli.CommandLine.MissingParameterException;

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
    public  ResponseEntity<Person> getPersonByName(
    		@RequestParam String firstName,
    		@RequestParam String lastName)
    {
		logger.info("Search for person ; {} {}", firstName,lastName);
		
        return new ResponseEntity<>(personService.getPersonByName(firstName,lastName), HttpStatus.FOUND);
    }
	
	/**
	 * Create - Add a new person
	 * @param {Person} - Model as object
	 * @return Person added
	 */
	@PostMapping(value ="/person")
	public ResponseEntity<Person> addNewPerson(
			@RequestBody Person person)
	{
		logger.info("Person to add in persons list : {}", person);
		
		return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
	}
    
	/**
	 * Update Person - Modif info of a person by name
	 * @param {Person} Body
	 * @return - Update new data to person
	 */
    @PutMapping(value = "/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person)
    {
		logger.info("Search to update : {}", person);
		if(person.getFirstName()==null && person.getLastName()==null)
		{
			logger.error("No body found! Please check your requestBody");
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
		else if (person != personService.getPersonByName(person.getFirstName(), person.getLastName()))
		{
			logger.error("Person does not exist! Pleace check and retry");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
	    {
	        return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
	    }
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
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/*********************************************************
	@PutMapping(value="/person", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> editPerson(@RequestBody Person person) {
        LOGGER.info("Requête Put reçue à /person");
        if(person == null) {
            LOGGER.error("pas de body fourni");
            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
        }
        else {
            personService.editPerson(person);
            return new ResponseEntity<>("Person updated successfully", HttpStatus.OK);
        }
     ****************************************************/
}