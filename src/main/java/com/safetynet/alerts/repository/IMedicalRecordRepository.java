package com.safetynet.alerts.repository;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {
	
	public List<MedicalRecord> getAllMedicalRecord();
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	
	public Map<String, String> addMedication(Map<String, String> medication);
	public List<String> addAllergie(List<String> allergie);
}