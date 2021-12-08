package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonService {
	
	public List<Person> getAllPersons();
	
	public Person addPerson(Person person);
	public Person getPersonByName(String firstName, String lastName);
	public Person updatePersonInfo(Person person);
	
	public void deletePerson(Person person);
}