package com.safetynet.alerts.repository;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository 
{
	private static Logger logger = LogManager.getLogger("PersonRepository");
	
    private List<Person> personList = new ArrayList<>();

	@Override
	public List<Person> getAllPerson() 
	{
		logger.info("Person list found!");
		return this.personList;
	}
	
	@Override
	public Person addPerson(Person person) 
	{
		personList.add(person);
		logger.info("Successfully added to persons list!");	
		return person;
	}
	
	@Override
	public Person updatePerson(Person person) 
	{	
		Person personToUpdate = personList.stream()
				.filter(p -> p.getLastName().equals(person.getLastName()))
				.findAny().orElseThrow(null);
		this.personList.set(personList.indexOf(personToUpdate), person);
		logger.info("Successfully updated to persons list!");
		return person;
	}

	@Override
	public void deletePerson(Person person) 
	{
		logger.info("Successfully deleted from persons list!");
		personList.remove(person);	
	}
}