package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonRepository
{	
	/**
	 * {@inheritDoc}
	 */
	public List<Person> getAllPerson();
	
	/**
	 * {@inheritDoc}
	 */
	public Person addPerson(Person person);
	
	/**
	 * {@inheritDoc}
	 */
	public Person updatePerson(Person person);
	
	/**
	 * {@inheritDoc}
	 */
	public void deletePerson(Person person);
}