package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository 
{
    private List<Person> personList = new ArrayList<>();

	@Override
	public List<Person> getAllPerson() 
	{
		return personList;
	}
	
	@Override
	public Person addPerson(Person person) 
	{
		personList.add(person);
		return person;
	}
	
	@Override
	public Person getPersonByName(String firstName, String lastName) 
	{	
		for (Person person : personList) {
			if (person.getFirstName().equals(firstName) &&
					person.getLastName().equals(lastName)) {
				return personList.get(personList.indexOf(person));
			}
		}
		return null;
	}
	
	@Override
	public Person updatePerson(Person person) 
	{
		personList.set(personList.indexOf(getPersonByName(
				person.getFirstName(), person.getLastName())), person);
		return person;
	}

	@Override
	public void deletePerson(Person person) 
	{
		personList.remove(person);	
	}
}