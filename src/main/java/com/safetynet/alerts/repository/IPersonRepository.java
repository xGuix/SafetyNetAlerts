package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonRepository {
	
	public Person addPerson(Person person);
	public List<Person> getAllPerson();
}