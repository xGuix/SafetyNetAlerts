package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository
{
	private static Logger logger = LogManager.getLogger("MedicalRecordRepository");
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

	@Override
	public List<MedicalRecord> getAllMedicalRecords()
	{
		return this.medicalRecordList;
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord)
	{
		this.medicalRecordList.add(medicalRecord);
		return medicalRecord;
	}

	public MedicalRecord getMedicalRecordByName(String firstName, String lastName)
	{
		for (MedicalRecord medicalRecord : medicalRecordList)
		{
			if(medicalRecord.getFirstName().equals(firstName) &&
					medicalRecord.getLastName().equals(lastName))
			{
				logger.info("Medical record : {} {} is sent" , firstName, lastName);
				
				return medicalRecord;
			}
		}
		logger.info("No medical record found ! Please check if typing error occurred");
		return null;
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord)
	{
		this.medicalRecordList.set(medicalRecordList.indexOf(getMedicalRecordByName(
				medicalRecord.getFirstName(),
				medicalRecord.getLastName())),
				medicalRecord);
		return medicalRecord;
	}

	public void deleteMedicalRecord(MedicalRecord medicalRecord)
	{
		medicalRecordList.remove(medicalRecord);		
	}
}