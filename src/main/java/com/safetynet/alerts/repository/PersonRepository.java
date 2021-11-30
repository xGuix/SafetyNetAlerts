package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository {
	
    private List<Person> personList = new ArrayList<>();

	@Override
	public Person addPerson(Person person) {
		this.personList.add(person);
		return person;
	}

	@Override
	public List<Person> getAllPerson() {
		return this.personList;
	}
}