package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonRepository
{	
	public List<Person> getAllPerson();
	public Person getPersonByStation(String firstName, String lastName);
	public Person addPerson(Person person);
	public Person updatePerson(Person person);
	public void deletePerson(Person person);
}