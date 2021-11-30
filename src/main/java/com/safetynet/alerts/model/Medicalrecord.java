package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medicalrecord {
	 private String firstName;
	 private String lastName;
	 private String birthcate;
	 private Map<String , Integer> medications;
	 private List<String> allergies;
	 
	 public Medicalrecord(String firstName, String lastName, String birthcate, Map<String, Integer> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthcate = birthcate;
		this.medications = medications;
		this.allergies = allergies;
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

	public String getBirthcate() {
		return birthcate;
	}

	public void setBirthcate(String birthcate) {
		this.birthcate = birthcate;
	}

	public Map<String, Integer> getMedications() {
		return medications;
	}

	public void setMedications(HashMap<String, Integer> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}
	 
}