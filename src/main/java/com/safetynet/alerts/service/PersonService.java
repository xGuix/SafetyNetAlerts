package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {
	
	private static Logger logger = LogManager.getLogger("PersonService");
	
	@Autowired
	private PersonRepository personRepository;
    
	public List<Person> getAllPersons(){
		logger.info("Person list Send");
		return this.personRepository.getAllPerson();
	}
	
	@Override
    public Person getPersonByName(String firstName, String lastName) {
		logger.info("Request sent with this : {} & {} parameters", firstName,lastName);
    	return personRepository.getPersonByName(firstName,lastName);
    }

	@Override
	public Person addPerson(Person person) {
		logger.info("Person add : {}", person);
		return this.personRepository.addPerson(person);
	}

	@Override
	public void deletePerson(Person person) {
		logger.info("Person delete : {}", person);
		this.personRepository.deletePerson(person);
	}
}