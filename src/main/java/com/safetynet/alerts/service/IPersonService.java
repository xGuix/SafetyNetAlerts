package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonService 
{
	/**
	 * {@inheritDoc}
	 */
	public List<Person> getAllPersons();
	
	/**
	 * {@inheritDoc}
	 */
	public Person getPersonByName(String firstName, String lastName);
	
	/**
	 * {@inheritDoc}
	 */
	public List<Person> getFamilyWithLastName(String address,String lastName);
	
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