package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {
	
	public List<MedicalRecord> getAllMedicalRecord();
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

	//List<String> addMedication(List<String> medication);
	//List<String> addAllergie(List<String> allergie);

}