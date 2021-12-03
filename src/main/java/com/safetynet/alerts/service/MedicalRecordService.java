package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {
	
	@Autowired
	private MedicalRecordRepository medicalRecordnRepository;
    
	public List<MedicalRecord> getAllMedicalRecords(){
		return this.medicalRecordnRepository.getAllMedicalRecord();
	}
}