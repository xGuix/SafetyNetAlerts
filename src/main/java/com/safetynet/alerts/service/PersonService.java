package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.exception.AlreadyExistingException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService implements IPersonService
{
	@Autowired
	private PersonRepository personRepository;
	
	private static Logger logger = LogManager.getLogger("PersonService");
	
	/**
	 * Get all persons list from Repository
	 * 
	 * @return - Repository list
	 */
	public List<Person> getAllPersons()
	{
		logger.info("Getting persons list...");
		return personRepository.getAllPerson();
	}
	
	/**
	 * Read Persons :
	 * Get one person with name
	 * 
	 * @return - Person in the list
	 * @exception - {@link NotFoundException}
	 */
	@Override
    public Person getPersonByName(String firstName, String lastName) 
	{
		logger.info("Searching match for Person with '{} {}'",firstName,lastName);
		return personRepository.getAllPerson().stream()
	    		.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
	    		.findAny().orElseThrow(() -> new NotFoundException("Person does not exists"));
    }
	
	/**
	 * Add Person :
	 * Add one person to the list
	 * 
	 * @return - Person added
	 */
	@Override
	public Person addPerson(Person person) 
	{
		if (personRepository.getAllPerson().stream()
				.anyMatch (p -> p.getFirstName().equals(person.getFirstName())
						&& p.getLastName().equals(person.getLastName())))
		{
			throw new AlreadyExistingException("Person already exists");
		}
		else {
			this.personRepository.addPerson(person);
		}		
		return person;
	}
	
	/**
	 * Update Person :
	 * Setup one person from the list
	 * 
	 * @return - Person updated
	 */
	@Override
	public Person updatePerson(Person person) 
	{
		return personRepository.updatePerson(person);
	}
	
	/**
	 * Delete Person :
	 * Remove person from the list
	 * 
	 * @NoReturn
	 */
	@Override
	public void deletePerson(Person person) 
	{	
		if (personRepository.getAllPerson().stream()
				.anyMatch (p -> p.getFirstName().equals(person.getFirstName())
						&& p.getLastName().equals(person.getLastName())))
		{
			this.personRepository.deletePerson(person);
		}
		else {
		    throw new NotFoundException("Firestation does not exists");
		}
	}
}