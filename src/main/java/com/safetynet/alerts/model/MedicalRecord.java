package com.safetynet.alerts.model;

import java.util.List;
import java.util.Objects;

import groovy.transform.Generated;

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
	
	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", medication=" + medication + ", allergie=" + allergie + "]";
	}
	
	@Generated
	@Override
	public int hashCode() {
		return Objects.hash(allergie, birthdate, firstName, lastName, medication);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalRecord other = (MedicalRecord) obj;
		return Objects.equals(allergie, other.allergie) && Objects.equals(birthdate, other.birthdate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(medication, other.medication);
	}
}