package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

@RestController
public class PersonsController {
	
	private static Logger logger = LogManager.getLogger("AlertsController");
	
	@Autowired
	private IPersonService personService;
		
	@GetMapping("/")
	public String index() {
		logger.info("Index reached");
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/persons")
	public List<Person> getAllPersons(){
		logger.info("List of persons reached");
		return this.personService.getAllPersons();
	}
}