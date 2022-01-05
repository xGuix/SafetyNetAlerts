package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordService
{
	public List<MedicalRecord> getAllMedicalRecords();
	public MedicalRecord getMedicalRecordByName(String firstName, String lastName);
	public int getHowOld(String firstName, String lastName);
	
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);
	
	public void deleteMedicalRecord(String firstName, String lastName);
}