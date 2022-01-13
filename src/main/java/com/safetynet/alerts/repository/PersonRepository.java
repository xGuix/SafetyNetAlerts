package com.safetynet.alerts.repository;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository 
{
	private static Logger logger = LogManager.getLogger("PersonRepository");
	
	/**
	 * Person List in memory
	 */
    private List<Person> personList = new ArrayList<>();
    
    /**
	 * Getter Person for integrationTest
	 * 
	 * @return personList Getter list
	 */
    public List<Person> getPersonList()
    {
        return personList;
    }

    /**
	 * Setter Person for integrationTest
	 * 
	 * @param personList Setter list
	 */
    public void setPersonList(List<Person> personList)
    {
        this.personList = personList;
    }
    
	/**
	 * Full List in memory
	 * 
	 * @return personList List of all persons
	 */
	@Override
	public List<Person> getAllPerson() 
	{
		logger.info("Person list found!");
		return this.personList;
	}
	
	/**
	 * Add person to the list
	 * 
	 * @return person added
	 */
	@Override
	public Person addPerson(Person person) 
	{
		personList.add(person);
		logger.info("Successfully added to persons list");	
		return person;
	}
	
	/**
	 * Update person to the list
	 * Find and update the person if existing
	 * 
	 * @return person updated
	 * @exception NotFoundException Throws when not found
	 */
	@Override
	public Person updatePerson(Person person) 
	{	
		Person personToUpdate = personList.stream()
			.filter(p -> 
					p.getFirstName().equals(person.getFirstName()) &&
					p.getLastName().equals(person.getLastName()))
			.findAny()
			.orElseThrow(() -> new NotFoundException("Person does not exists"));
		
		this.personList.set(personList.indexOf(personToUpdate), person);
		logger.info("Successfully updated to persons list : {}",person);
		return person;
	}
	
	/**
	 * Delete person from the list
	 * 
	 * @param person Deleted
	 */
	@Override
	public void deletePerson(Person person) 
	{
		logger.info("Successfully deleted from persons list : {}",person);
		personList.remove(person);	
	}
}