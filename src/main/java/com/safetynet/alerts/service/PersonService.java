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
	 * Setter Person for integrationTest
	 * 
	 * @param personRepository Setter repository
	 */
	public void setPersonRepository(PersonRepository personRepository)
	{
		this.personRepository = personRepository;
	}

	/**
	 * Get all persons list from Repository
	 * 
	 * @return Repositorylist List of all person
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
	 * @param firstName Person first name
	 * @param lastName Person last name
	 * @return Person One person
	 * @exception NotFoundException Throws when not found
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
	 * @param person Person to add
	 * @return Person added
	 * @throws AlreadyExistingException Throws when exist
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
	 * @param person Person to update
	 * @return Person updated
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
	 * @param person Person to delete
	 * @throws NotFoundException Throws when not found
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