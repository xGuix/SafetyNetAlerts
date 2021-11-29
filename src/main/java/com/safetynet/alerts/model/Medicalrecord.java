package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Medicalrecord {
	 private String firstName;
	 private String lastName;
	 private String birthcate;
	 private HashMap<String , Integer> medications;
	 private ArrayList<String> allergies;
	 
	 public Medicalrecord(String firstName, String lastName, String birthcate, HashMap<String, Integer> medications, ArrayList<String> allergies) {
		super();
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

	public HashMap<String, Integer> getMedications() {
		return medications;
	}

	public void setMedications(HashMap<String, Integer> medications) {
		this.medications = medications;
	}

	public ArrayList<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}
	 
}
