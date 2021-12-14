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
	public Person addPerson(Person person) {
		this.personList.add(person);
		logger.debug("Person {} is added to the list", person);
		return person;
	}
	
	@Override
	public Person getPersonByName(String firstName, String lastName) {
		for (Person person : personList) {
			if(person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				logger.info("Person found : {} {} is sent" , firstName, lastName);
				return person;
			}
		}
		logger.info("No person found ! Please check if typing error occurred");
		return null;
    }

	public Person updatePerson(Person person) {
		if (personList.contains(getPersonByName(person.getFirstName(), person.getLastName()))) {
			personList.set(personList.indexOf(getPersonByName(person.getFirstName(), person.getLastName())), person);
			logger.debug("Person infos {} is updated", person);
			return person;
		}
		else {
			logger.debug("Person {} does not existe!", person);
			return person;
		}
	}

	@Override
	public void deletePerson(Person person) {
		if (personList.contains(getPersonByName(person.getFirstName(), person.getLastName()))) {
			personList.remove(person);
			logger.debug("Person {} is deleted from the list", person);
		}
		else {
			logger.debug("Person {} does not existe!", person);
		}
	}
}