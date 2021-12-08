package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository {
	
	private static Logger logger = LogManager.getLogger("PersonRepository");
	
    private List<Person> personList = new ArrayList<>();

	@Override
	public List<Person> getAllPerson() {
		return this.personList;
	}
	
	@Override
	public Person getPersonByName(String firstName, String lastName) {
		for (Person person : personList) {
			if(person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				logger.info("Person found : {} {}" , firstName, lastName);
				return person;
			}
		}
		logger.info("No person found ! Please check if typing error occurred");
    	return null;
    }

	@Override
	public Person addPerson(Person person) {
		this.personList.add(person);
		logger.info("Person {} add to the list", person);
		return person;
	}
	
	@Override
	public void deletePerson(Person person) {
		personList.remove(person);
		logger.info("Person {} delete from the list", person);
	}
}