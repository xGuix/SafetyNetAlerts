package com.safetynet.alerts;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	
	 private String firstName;
	 private String lastName;
	 private String birthdate;
	 private String address;
	 private String city;
	 private String zip;
	 private String phone;
	 private String email;
	 private String station;
	 private Map<String, String> medications;
	 private List<String> allergies;


	@PostConstruct
	public void readJsonData() throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		// InputStream jsonFile reader
		File src = new File(dataFilePath);
		// JsonNode avec mapper.readTreee pour mapper le contenu
		JsonNode root = mapper.readTree(src);
		
		// Persons List
		JsonNode persons = root.path("persons");
		for (Iterator<JsonNode> iteratorPerson = persons.iterator(); iteratorPerson.hasNext();) {
			JsonNode readPerson = iteratorPerson.next();
		 	Person person = new Person(firstName, lastName, address, city, zip, phone, email);
			person.setFirstName(readPerson.path("firstName").asText());
			person.setLastName(readPerson.path("lastName").asText());
			person.setAddress(readPerson.path("address").asText());
			person.setCity(readPerson.path("city").asText());
			person.setZip(readPerson.path("zip").asText());
		 	person.setPhone(readPerson.path("phone").asText());
		 	person.setEmail(readPerson.path("email").asText());
		 	personRepository.addPerson(person);
		}
		// Firestations List
		JsonNode firestations = root.path("firestations");
		for (Iterator<JsonNode> iteratorFirestation = firestations.iterator(); iteratorFirestation.hasNext();) {
			JsonNode readfirestations = iteratorFirestation.next();
			Firestation firestation = new Firestation(address, station);
			firestation.setAddress(readfirestations.path("address").asText());
			firestation.setStation(readfirestations.path("station").asText());
			firestationRepository.addFirestation(firestation);
		}
		// MedicalRecords List
		JsonNode medicalRecords = root.path("medicalrecords");
		for (Iterator<JsonNode> iteratorMedicalRecord = medicalRecords.iterator(); iteratorMedicalRecord.hasNext();) {
			JsonNode readMedicalRecord = iteratorMedicalRecord.next();
			MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthdate);
			medicalRecord.setFirstName(readMedicalRecord.path(firstName).asText());
			medicalRecord.setLastName(readMedicalRecord.path(lastName).asText());
			medicalRecord.setBirthdate(readMedicalRecord.path(birthdate).asText());
			
			JsonNode medications = root.path("medications");
			for (Iterator<JsonNode> iteratorMedication = medications.iterator(); iteratorMedication.hasNext();) {
				JsonNode readMedication = iteratorMedication.next();
				//MedicalRecord medication = new MedicalRecord(medications);
				medicalRecord.setMedications((Map<String, String>) readMedication.fields());
				//medicalRecord.setAllergies(readMedicalRecord.path("allergies").findValues(allergie, allergies));
				medicalRecordRepository.addMedicalRecord(medicalRecord);
			}
		}
	}
}