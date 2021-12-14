package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {
	
	public List<MedicalRecord> getAllMedicalRecord();	
	public MedicalRecord getMedicalRecordByName(String firstName, String lastName);
	
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);
	
	public void deleteMedicalRecord(MedicalRecord medicalRecord);
}