package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public abstract class PersonService implements PersonRepository {
	
	private PersonRepository personRepository;
    
	protected PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Iterable<Person> list(){
		return personRepository.findAll();
	}
	
    @PostConstruct
    private void postConstruct() throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.readValue(new File("json/data.json"),Data.class);
    }
}