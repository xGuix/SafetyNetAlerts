package com.safetynet.alerts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;

@Service
public class JsonLoader {
	
	@Autowired
	private IPersonRepository personRepository;
	
	@Value("${dataSourceJson}")
	private String dataFilePath;
	
	@PostConstruct
	public void readJsonData() {
		Person person = new Person("toto", "tata", dataFilePath, dataFilePath, dataFilePath, dataFilePath, dataFilePath);
		//InputSream pour lire le fichier json
		//JsonNode avec mapper.readTreee pour mapper le contenu
		personRepository.addPerson(person);
	}
}