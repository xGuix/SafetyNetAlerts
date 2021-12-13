package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

@RestController
public class PersonsController {
	
	private static Logger logger = LogManager.getLogger("PersonsController");
	
	@Autowired
	private IPersonService personService;
	
	/**
	 * Read - Get all persons
	 * @return - Full persons list
	 */
	@GetMapping(value = "/persons")
	public List<Person> getAllPersons(){
		logger.info("Persons list to find");
		return this.personService.getAllPersons();
	}
	
	/**
	 * Read - Get a person by name
	 * @param firstName & lastName
	 * @return - The person data
	 */
    @GetMapping(value ="/person")
    public Person getPersonByName(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("Person to find");
        return personService.getPersonByName(firstName,lastName);
    }
	
	/**
	 * Create - Add a new person
	 * @param Person - Model as object
	 * @return Person added
	 */
	@PostMapping(value ="/person")
	public Person addNewPerson(@RequestBody Person person) {
		logger.info("Person to add in persons list");
		return personService.addPerson(person);
	}
    
	/**
	 * Update Person - Modif info of a person by name
	 * @param firstName & lastName
	 * @return - Update new data to person
	 */
    @PatchMapping(value = "/person")
    public Person updatePerson(@RequestParam  String firstName , @RequestParam String lastName, @RequestBody Person person ) {
		logger.info("Person info to update");
        return personService.updatePerson(person);
    }
	
	/**
	 * Delete - Delete a person
	 * @param {firstName} & {lastName} - Person to delete
	 */
	@DeleteMapping(value = "/person")
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("Person to delete from the list");
		personService.deletePerson(getPersonByName(firstName, lastName));
	}
}