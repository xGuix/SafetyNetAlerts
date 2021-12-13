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
		logger.info("Person list found");
		return personRepository.getAllPerson();
	}
	
	@Override
    public Person getPersonByName(String firstName, String lastName) {
		logger.info("Person : {} {} is found", firstName,lastName);
    	return personRepository.getPersonByName(firstName,lastName);
    }
	
	@Override
	public Person addPerson(Person person) {
		logger.info("Person added : {}", person);
		return personRepository.addPerson(person);
	}
	
	@Override
	public void deletePerson(Person person) {
		logger.info("Person : {} {} is delete", person.getFirstName(),person.getLastName());
		personRepository.deletePerson(person);
	}
	
	@Override
	public Person updatePerson(Person person) {
		logger.info("New person info : {} {} is update", person.getFirstName(),person.getLastName());
		return personRepository.updatePerson(person);
	}
}