package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService
{
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
    
	public List<MedicalRecord> getAllMedicalRecords(){
		return this.medicalRecordRepository.getAllMedicalRecords();
	}

	@Override
	public MedicalRecord getMedicalRecordByName(String firstName, String lastName) {
		return medicalRecordRepository.getMedicalRecordByName(firstName,lastName);
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.addMedicalRecord(medicalRecord);
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.updateMedicalRecord(medicalRecord);
	}

	@Override
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);	
	}
}