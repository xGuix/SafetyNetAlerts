package com.safetynet.alerts.model;

import java.util.List;

public class MedicalRecord {
	
	 private String firstName;
	 private String lastName;
	 private String birthdate;
	 private List<String> medication;
	 private List<String> allergie;
	 
	 public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medication, List<String> allergie) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medication = medication;
		this.allergie = allergie;
	}
	 
	public MedicalRecord() {
		// use empty method
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

	public List<String> getMedication() {
		return medication;
	}
	
	public void setMedication(List<String> medication) {
		this.medication = medication;
	}

	public List<String> getAllergie() {
		return allergie;
	}

	public void setAllergie(List<String> allergie) {
		this.allergie = allergie;
	}
}