package com.safetynet.alerts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class AlertsController {
	
	private static Logger logger = LogManager.getLogger("AlertsController");
		
	@GetMapping("/")
	public String index() {
		logger.info("Index reached");
		return "Greetings from Spring Boot!";
	}
	
	private PersonService personService;
	
	public void PersonController (PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/list")
	public Iterable<Person> list() {
		logger.info("PersonsList show");
		return personService.findAll();
	    }
	}