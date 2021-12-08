package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
		logger.info("Persons list found");
		return this.personService.getAllPersons();
	}
	
	/**
	 * Read - Get a person by name
	 * @return - The person data
	 */
    @GetMapping("/person")
    public Person getPersonByName(@RequestParam String firstName, @RequestParam String lastName ) {
		logger.info("Person Infos found");
        return personService.getPersonByName(firstName,lastName);
    }
	
	/**
	 * Create - Add a new person
	 * @param Person Model as object
	 * @return Person added
	 */
	@PostMapping("/person")
	public Person addNewPerson(@RequestBody Person person) {
		logger.info("Person is added to the persons list");
		return personService.addPerson(person);
	}
	
	/**
	 * Delete - Delete a person
	 * @param {firstName} & {lastName} - Personto delete
	 */
	@DeleteMapping("/person")
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		personService.deletePerson(personService.getPersonByName(firstName, lastName));
		logger.info("Persons is deleted from the list");
	}
}