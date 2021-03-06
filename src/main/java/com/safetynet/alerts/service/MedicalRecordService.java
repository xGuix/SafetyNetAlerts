package com.safetynet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.exception.AlreadyExistingException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService
{
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	private static Logger logger = LogManager.getLogger("MedicalRecordService");
    
	/**
	 * Set MedicalRecordRepository for integrationTest
	 * 
	 * @param medicalRecordRepository Setter medicalRecordRepository
	 */
	public void setMedicalRecordRepository(MedicalRecordRepository medicalRecordRepository)
	{
		this.medicalRecordRepository = medicalRecordRepository;
	}
	
	/**
	 * Get all list of medical record from Repository
	 * 
	 * @return Repositorylist Data in memory
	 */
	public List<MedicalRecord> getAllMedicalRecords()
	{
		logger.debug("Getting medical records list...");
		return medicalRecordRepository.getAllMedicalRecords();
	}
	
	/**
	 * Read Medical record :
	 * Search of person existing and
	 * Get the person with his name
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return MedicalRecord For one Person
	 * @exception NotFoundException Throws when not found
	 */
	@Override
	public MedicalRecord getMedicalRecordByName(String firstName, String lastName)
	{
		logger.info("Searching match for name '{} {}'",firstName,lastName);
		return medicalRecordRepository.getAllMedicalRecords().stream()
	    		.filter(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))
	    		.findAny().orElseThrow(() -> new NotFoundException("Medical Record does not exists"));
	}
	
	/**
	 * Get How Old is this Person
	 * Get birthdate from medical records
	 * then calculate the age of person
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return YearsOld int age
	 */
	public int getHowOld(String firstName, String lastName)
	{
		MedicalRecord personToFind = getAllMedicalRecords().stream()
	           .filter(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))
	           .findAny().orElseThrow(()-> new NotFoundException("Person does not exists"));
		
		String birthdate = personToFind.getBirthdate();	
		DateTimeFormatter setupDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Period yearsOld = LocalDate.parse(birthdate, setupDate).until(LocalDate.now());
        
		logger.debug("Calculating age of {} {}",firstName,lastName);
	    return yearsOld.getYears();
	}
	
	/**
	 * Add Medical Record :
	 * Search if file existing and
	 * Add the Medical record to the list
	 * 
	 * @param medicalRecord Full medical record body
	 * @return MedicalRecord added
	 * @exception AlreadyExistingException Throws when exists
	 */
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord)
	{
		logger.info("Searching match for medical record {}", medicalRecord);
		if (medicalRecordRepository.getAllMedicalRecords().stream()
				.anyMatch(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) 
						&& mr.getLastName().equals(medicalRecord.getLastName())))
		{
			throw new AlreadyExistingException("Medical Record already exists");
		}
		else {
			logger.info("Not Found! Sending to medical record list {}", medicalRecord);
			this.medicalRecordRepository.addMedicalRecord(medicalRecord);
		}
		return medicalRecord;
	}
	
	/**
	 * Update Medical Record :
	 * Send parameter to the repository for checking
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return medicalRecord udated
	 */
	@Override
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord)
	{
		return medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecord);
	}

	/**
	 * Delete Medical Record :
	 * Search file by name and remove it from the list
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @exception NotFoundException Throws when not found
	 */
	@Override
	public void deleteMedicalRecord(String firstName, String lastName)
	{
		logger.info("Searching match for medical record of {} {}", firstName,lastName);
		if (medicalRecordRepository.getAllMedicalRecords().stream()
				.anyMatch(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)))
		{
			logger.info("Exists! Deleting from medical record list");
			medicalRecordRepository.deleteMedicalRecord(getMedicalRecordByName(firstName, lastName));	
		}
		else {
		    throw new NotFoundException("Medical Record does not exists");
		}
	}
}