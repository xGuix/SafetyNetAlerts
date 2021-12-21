package com.safetynet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

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
		return personRepository.getAllPerson();
	}
	
	@Override
	public Person addPerson(Person person) 
	{
		return personRepository.addPerson(person);
	}
	
	
	@Override
    public Person getPersonByName(String firstName, String lastName) 
	{
		logger.info("No match! Person not Found.");
		return personRepository.getAllPerson().stream()
				.filter(p -> {
					p.getFirstName().equals(firstName);
					p.getLastName().equals(lastName);
					})
				.collect(Collectors.toList());
    }
	
	@Override
	public Person updatePerson(Person person) 
	{
		for (Person personToUpdate : personRepository.getAllPerson()) 
		{
			if (personToUpdate.getFirstName().equals(person.getFirstName()) &&
					personToUpdate.getLastName().equals(person.getLastName())) 
			{
				return personRepository.updatePerson(person);
			}
		}
		return null;
	}
	
	@Override
	public void deletePerson(Person person) 
	{
		if (personRepository.getAllPerson().contains(person)) 
		{
			personRepository.deletePerson(person);
		}
	}
}