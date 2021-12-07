package com.safetynet.alerts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IFirestationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;

@Service
public class JsonLoader {
	
	@Autowired
	private IPersonRepository personRepository;
	@Autowired
	private IFirestationRepository firestationRepository;
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;
	
	@Value("${dataSourceJson}")
	private String dataFilePath;
	
	private List<String> medicationList;
	private List<String> allergieList;
	
	@PostConstruct
	public void readJsonData() throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
				
		// InputStream jsonFile reader with JsonNode avec mapper.readTree
		JsonNode root = mapper.readTree(new FileInputStream(dataFilePath));
		
		loadPersonData(root);
		loadFireStationData(root);
		loadMedicalRecordData(root);
	}
	
	public void loadPersonData(JsonNode root) {
	JsonNode persons = root.path("persons");
	for (JsonNode nodePerson : persons) {
		
	 	Person person = new Person();
		person.setFirstName(nodePerson.path("firstName").asText());
		person.setLastName(nodePerson.path("lastName").asText());
		person.setAddress(nodePerson.path("address").asText());
		person.setCity(nodePerson.path("city").asText());
		person.setZip(nodePerson.path("zip").asText());
	 	person.setPhone(nodePerson.path("phone").asText());
	 	person.setEmail(nodePerson.path("email").asText());
	 	personRepository.addPerson(person);
		}
	}
		
	public void loadFireStationData(JsonNode root) {
	JsonNode firestations = root.path("firestations");
	for (JsonNode nodeFirestation : firestations) {
			
		Firestation firestation = new Firestation();
		firestation.setAddress(nodeFirestation.path("address").asText());
		firestation.setStation(nodeFirestation.path("station").asText());
		firestationRepository.addFirestation(firestation);
		}
	}
		
	public void loadMedicalRecordData(JsonNode root) {
	JsonNode medicalRecords = root.path("medicalrecords");
	for (JsonNode nodeMedicalRecord : medicalRecords) {
			
		MedicalRecord medicalRecord = new MedicalRecord("firstName","lastName","birthdate", allergieList, allergieList);
		medicalRecord.setFirstName(nodeMedicalRecord.path("firstName").asText());
		medicalRecord.setLastName(nodeMedicalRecord.path("lastName").asText());
		medicalRecord.setBirthdate(nodeMedicalRecord.path("birthdate").asText());
		
		JsonNode medications = root.path("medications");
		medicationList = new ArrayList<>();
		for (JsonNode nodeMedication : medications) {
			medicationList.add(nodeMedication.path("medications").asText());
			}
		medicalRecord.setMedication(medicationList);
	
		JsonNode allergies = root.path("allergies");
		allergieList = new ArrayList<>();
		for (JsonNode nodeAllergie : allergies) {
			allergieList.add(nodeAllergie.path("allergies").asText());
			}	
		medicalRecord.setAllergie(allergieList);

		medicalRecordRepository.addMedicalRecord(medicalRecord);
		}
	}
}