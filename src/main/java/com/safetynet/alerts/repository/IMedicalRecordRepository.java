package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository
{
	/**
	 * {@inheritDoc}
	 */
	public List<MedicalRecord> getAllMedicalRecords();	
	
	/**
	 * {@inheritDoc}
	 */
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	
	/**
	 * {@inheritDoc}
	 */
	public MedicalRecord updateMedicalRecord(String firstName, String lastName,MedicalRecord medicalRecord);
	
	/**
	 * {@inheritDoc}
	 */
	public void deleteMedicalRecord(MedicalRecord medicalRecord);
}