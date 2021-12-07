package com.safetynet.alerts.repository;

import java.util.List;
import java.util.Map;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {
	
	public List<MedicalRecord> getAllMedicalRecord();
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	
	public Map<String, Object> addMedication(Map<String, Object> medication);
	public List<String> addAllergie(List<String> allergie);
}