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
    private Map<String, String> medicationMapList = new HashMap<>();
    private List<String> allergieList = new ArrayList<>();

	@Override
	public List<MedicalRecord> getAllMedicalRecord() {
		return this.medicalRecordList;
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecordList.add(medicalRecord);
		return medicalRecord;
	}

	@Override
	public Map<String, String> addMedication(Map<String, String> medication) {
		this.medicationMapList.putAll(medication);
		return medication;
	}

	@Override
	public List<String> addAllergie(List<String> allergie) {
		this.allergieList.addAll(allergie);
		return allergie;
	}
}