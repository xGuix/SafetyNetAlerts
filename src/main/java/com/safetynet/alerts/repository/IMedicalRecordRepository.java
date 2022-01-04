package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository
{
	public List<MedicalRecord> getAllMedicalRecords();	
	
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	public MedicalRecord updateMedicalRecord(String firstName, String lastName,MedicalRecord medicalRecord);
	
	public void deleteMedicalRecord(MedicalRecord medicalRecord);
}