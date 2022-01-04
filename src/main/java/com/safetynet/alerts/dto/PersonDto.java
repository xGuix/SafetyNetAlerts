package com.safetynet.alerts.dto;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public class PersonDto
{	
	private String firstName;
	private String lastName;
	private String birthdate;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    private List<String> firestationAddress;
    private List<MedicalRecord> medicalRecord;
      
	public PersonDto(
			String firstName, String lastName,String birthdate,
			String address, String city, String zip,
			String phone, String email,
			List<String>firestationAddress,
			List <MedicalRecord>medicalRecord)
	{	
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.setFirestationAddress(firestationAddress);
		this.setMedicalRecord(medicalRecord);
	}

	public PersonDto()
	{
		// Use Default method
	}

	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLasttName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(String birthdate)
	{
		this.birthdate = birthdate;
	}
	
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<String> getFirestationAddress()
	{
		return firestationAddress;
	}

	public void setFirestationAddress(List<String> firestationAddress)
	{
		this.firestationAddress = firestationAddress;
	}

	public List<MedicalRecord> getMedicalRecord()
	{
		return medicalRecord;
	}

	public void setMedicalRecord(List<MedicalRecord> medicalRecord)
	{
		this.medicalRecord = medicalRecord;
	}
}