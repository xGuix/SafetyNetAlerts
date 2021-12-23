package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository
{
	private static Logger logger = LogManager.getLogger("MedicalRecordRepository");
	
	/**
	 * Medical Records List in memory
	 */
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    /**
	 * Full Medical Record in List
	 * @return - {medicalRecordList} 
	 */
	@Override
	public List<MedicalRecord> getAllMedicalRecords()
	{
		return this.medicalRecordList;
	}

	/**
	 * Add medical record to the List
	 * @return - {medicalRecord} added
	 */
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord)
	{
		logger.info("Successfully added to Medical Record list");
		medicalRecordList.add(medicalRecord);
		return medicalRecord;
	}
	
	/**
	 * Update medical record from the List
	 * @return - {medicalRecord} updated
	 */
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord)
	{
		MedicalRecord medicalRecordToUpdate = medicalRecordList.stream()
				.filter(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))
				.findAny().orElseThrow(() -> new NotFoundException("Person does not exists"));
		logger.info("Successfully updated to medical record list");
		this.medicalRecordList.set(medicalRecordList.indexOf(medicalRecordToUpdate),medicalRecord);
		return medicalRecord;
	}

	/**
	 * Delete medical record from the List
	 * @NoReturn
	 */
	public void deleteMedicalRecord(MedicalRecord medicalRecord)
	{
		logger.info("Successfully deleted from Medical Record list");
		medicalRecordList.remove(medicalRecord);		
	}
}