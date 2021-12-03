package com.safetynet.alerts.model;

import java.util.List;
import java.util.Map;

public class MedicalRecord {
	
	 private String firstName;
	 private String lastName;
	 private String birthdate;
	 private Map<String , String> medication;
	 private List<String> allergie;
	 
	 public MedicalRecord(String firstName, String lastName, String birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
	}
	 
	public MedicalRecord( Map<String, String> medication) {
		this.medication = medication;
	}
	
	public MedicalRecord( List<String> allergie) {
		this.allergie = allergie;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Map<String, String> getMedications() {
		return medication;
	}

	public void setMedications(Map<String, String> medications) {
		this.medication = medications;
	}

	public List<String> getAllergies() {
		return allergie;
	}

	public void setAllergies(List<String> allergie) {
		this.allergie = allergie;
	}
}