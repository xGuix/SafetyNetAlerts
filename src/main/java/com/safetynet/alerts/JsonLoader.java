package com.safetynet.alerts;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;

@Service
public class JsonLoader {
	
	@Autowired
	private IPersonRepository personRepository;
	
	@Value("${dataSourceJson}")
	private String dataFilePath;
	
	@PostConstruct
	public void readJsonData() throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		//InputStream jsonFile reader
		File src = new File(dataFilePath);
		try {
			//JsonNode avec mapper.readTreee pour mapper le contenu
			JsonNode root = mapper.readTree(src);
			JsonNode persons = root.path("persons");

			for (Iterator<JsonNode> iterator = persons.iterator(); iterator.hasNext();) {
				JsonNode readPerson = iterator.next();
			 	Person person = new Person("firstName", "lastName", "address", "city", "zip", "phone", "email");
			 	
				person.setFirstName(readPerson.path("firstName").textValue());
				person.setLastName(readPerson.path("lastName").textValue());
				person.setAddress(readPerson.path("address").textValue());
				person.setCity(readPerson.path("city").textValue());
				person.setZip(readPerson.path("zip").textValue());
			 	person.setPhone(readPerson.path("phone").textValue());
			 	person.setEmail(readPerson.path("email").textValue());
			 	
			 	personRepository.addPerson(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}