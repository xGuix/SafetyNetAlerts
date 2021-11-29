package com.safetynet.alerts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class AlertsController {
	
	private static Logger logger = LogManager.getLogger("AlertsController");
	
	@Autowired
	private PersonService personService;
		
	@GetMapping("/")
	public String index() {
		logger.info("Index reached");
		return "Greetings from Spring Boot!";
	}
	
	public void PersonController (PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/list") //(value ="/list", params = "RequestMethod.GET", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Person> list(){
		logger.info("PersonList show");
		return personService.list();
	}
	
	@GetMapping("/person") //(value ="/person", params = "RequestMethod.GET", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Person> person() {
		logger.info("PersonList show");
		return personService.personList();
	    }
	}