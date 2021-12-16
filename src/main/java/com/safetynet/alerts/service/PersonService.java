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
		for (Person personToFind : personRepository.getAllPerson()) 
		{
			if(personToFind.getFirstName().equals(firstName) && 
					personToFind.getLastName().equals(lastName)) 
			{
				
				return personRepository.getPersonByStation(firstName, lastName);
			}
		}
		logger.info("No match! Person not Found.");
		return null;
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