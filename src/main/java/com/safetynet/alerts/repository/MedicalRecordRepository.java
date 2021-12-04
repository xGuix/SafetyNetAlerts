package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {
	
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private Map<String,String> medicationList = new HashMap<>();

	@Override
	public List<MedicalRecord> getAllMedicalRecord() {
		return this.medicalRecordList;
	}
	
	@Override
	public Map<String, String> addMedication(String treatment, String dosage) {
		return this.medicationList;
	}
	
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecordList.add(medicalRecord);
		return medicalRecord;
	}

	@Override
	public void addMedication(HashMap<String, String> medication) {
		return;
		
	}
}