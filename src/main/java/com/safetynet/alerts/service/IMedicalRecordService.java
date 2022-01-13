package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordService
{
	/**
	 * {@inheritDoc}
	 */
	public List<MedicalRecord> getAllMedicalRecords();
	
	/**
	 * {@inheritDoc}
	 */
	public MedicalRecord getMedicalRecordByName(String firstName, String lastName);
	
	/**
	 * {@inheritDoc}
	 */
	public int getHowOld(String firstName, String lastName);
	
	/**
	 * {@inheritDoc}
	 */
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	
	/**
	 * {@inheritDoc}
	 */
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);
	
	/**
	 * {@inheritDoc}
	 */
	public void deleteMedicalRecord(String firstName, String lastName);
}