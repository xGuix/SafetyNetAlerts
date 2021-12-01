package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

@RestController
public class PersonsController {
	
	private static Logger logger = LogManager.getLogger("PersonsController");
	
	@Autowired
	private IPersonService personService;
		
	@GetMapping("/")
	public String index() {
		logger.info("Index reached");
		return "Welcome to SafetyNet Alerts API";
	}
	
	@GetMapping(value = "/persons")
	public List<Person> getAllPersons(){
		logger.info("Persons list is found");
		return this.personService.getAllPersons();
	}
	
    @GetMapping(value = "/personsList")
    public ResponseEntity<List<Person>> responseEntity(){
        logger.info("Show persons List");
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }
}