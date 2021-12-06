package com.safetynet.alerts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	 private String medications;
	 private Map<String,Object> medication = new HashMap<>();
	 private String allergies;
	 private List<String> allergie;

	@PostConstruct
	public void readJsonData() throws IOException {
		
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		
		// InputStream jsonFile reader
		//File src = new File(dataFilePath);
		InputStream src = new FileInputStream(dataFilePath);
		
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
			MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthdate, medication, allergie);
			medicalRecord.setFirstName(readMedicalRecord.path(firstName).asText());
			medicalRecord.setLastName(readMedicalRecord.path(lastName).asText());
			medicalRecord.setBirthdate(readMedicalRecord.path(birthdate).asText());
			
			// Medication to Map <treatment and dosage>
			JsonNode medicationPath = root.path("medicalrecords/medications");
			for (Iterator<JsonNode> iteratorMedication = medicationPath.iterator();  iteratorMedication.hasNext();) {
				//JsonNode readMedications =  iteratorMedication.next();

				HashMap<String,String> medicationMap = new HashMap<String,String>();
				for (Entry<String, String> medicationEntry : medicationMap.entrySet()) {
					medicationMap.put(medicationEntry.getKey(),medicationEntry.getValue());
					medication.put(medications, medicationMap);
					medicalRecord.setMedication(medication);
				}
				
				medicalRecordRepository.addMedication(medicationMap);
			}
			medicalRecordRepository.addMedicalRecord(medicalRecord);
			
			/****************************************
			
		    //TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
			medication = mapper.readValue(src, HashMap.class); 
			//medication = mapper.readValue(src, new TypeReference<HashMap<String, String>>() {});
			//JsonNode medicationPath = root.path("medications");
			for (Entry<String, String> medicationEntry : medication.entrySet()) {
				medication.put(medicationEntry.getKey(),medicationEntry.getValue());
				medicalRecordRepository.addMedication(medication);
				
			**************************************
			
			for (Iterator<JsonNode> iteratorMedication= medications.iterator();  iteratorMedication.hasNext();) {
				JsonNode readMedication =  iteratorMedication.next();
				medication = new HashMap<>(medication);
				medication.putAll(readMedication.at("/Medications"));
				
				medicalRecord.setMedication(medication);
				medicalRecordRepository.addMedication(medication);
			}
				
			} *****************************************/
			
			//Allergie List
			JsonNode allergies = root.path("allergies");
			for (Iterator<JsonNode> iteratorAllergie = allergies.iterator(); iteratorAllergie.hasNext();) {
				JsonNode readAllergie = iteratorAllergie.next();
				allergie.add(readAllergie.path("allergies").asText());
				medicalRecord.setAllergie(allergie);
				medicalRecordRepository.addAllergie(allergie);
			}	
		}
	}
}