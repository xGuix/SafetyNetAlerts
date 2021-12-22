package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService implements IPersonService
{
	@Autowired
	private PersonRepository personRepository;
	
	private static Logger logger = LogManager.getLogger("PersonService");
	
	public List<Person> getAllPersons()
	{
		logger.info("Getting persons list...");
		return personRepository.getAllPerson();
	}
	
	@Override
    public Person getPersonByName(String firstName, String lastName) 
	{
		logger.info("Searching match for Person with '{} {}'",firstName,lastName);
		return personRepository.getAllPerson().stream()
	    		.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
	    		.findAny().orElseThrow(null);
    }
	
	@Override
	public Person addPerson(Person person) 
	{
		return personRepository.addPerson(person);
	}
	
	@Override
	public Person updatePerson(Person person) 
	{
		return personRepository.updatePerson(person);
	}
	
	@Override
	public void deletePerson(Person person) 
	{
		personRepository.deletePerson(person);
	}
}