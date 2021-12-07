package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {
	
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

	@Override
	public List<MedicalRecord> getAllMedicalRecord() {
		return this.medicalRecordList;
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecordList.add(medicalRecord);
		return medicalRecord;
	}
}