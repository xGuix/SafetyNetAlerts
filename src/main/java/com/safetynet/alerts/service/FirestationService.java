package com.safetynet.alerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.exception.AlreadyExistingException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;

@Service
public class FirestationService implements IFirestationService 
{
	@Autowired
	private FirestationRepository firestationRepository;
    
	private static Logger logger = LogManager.getLogger("FirestationService");
	
	/**
	 * Setter Firestation for integrationTest
	 * 
	 * @param - {firestationRepository}
	 */
	public void setFirestationRepository(FirestationRepository firestationRepository)
	{
		this.firestationRepository = firestationRepository;
	}
	
	/**
	 * Get all list of firestation from Repository
	 * 
	 * @return - Repositorylist
	 */
	public List<Firestation> getAllFirestations()
	{
		return this.firestationRepository.getAllFirestation();
	}
	
	/**
	 * Read Firestation :
	 * Search firestation with N°Station
	 * Get list with Station number
	 * 
	 * @return - Firestation List
	 */
	@Override
    public List<Firestation> getFirestationsFor(String station)
	{
		logger.info("Firestation N°{} Check matches.", station);
		return firestationRepository.getAllFirestation().stream()
				.filter(f -> f.getStation().equals(station))
				.collect(Collectors.toList());
	}
	
	/**
	 * Read Firestation :
	 * Search addresses of Station N°
	 * Get list of addresses with number
	 * 
	 * @return - Addresses List
	 */
    public List<String> getOnlyAddressesFor(String station)
	{
		logger.info("Firestation N°{} Check matches.", station);
		return firestationRepository.getAllFirestation().stream()
				.filter(f -> f.getStation().equals(station))
				.map(Firestation::getAddress)
				.collect(Collectors.toList());
	}
	
	/**
	 * Read Firestation :
	 * Search One firestation with Address
	 * Get the Firestation with station & address
	 * 
	 * @return - Firestation
	 */
	@Override
	public Firestation getOneFirestation(String address, String station)
	{
		logger.info("Searching match address for Firestation N°{} with '{}'", station, address);
		return firestationRepository.getAllFirestation().stream()
	    		.filter(f -> f.getAddress().equals(address))
	    		.findAny().orElseThrow(() -> new NotFoundException("Address does not exists"));
	}
	
	/**
	 * Add Firestation :
	 * Search if fires existing and
	 * Add the firestation to the list
	 * 
	 * @return - Firestation added
	 * @exception - {@link AlreadyExistingException}
	 */
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		if (firestationRepository.getAllFirestation().stream()
				.anyMatch(f -> f.getAddress().equals(firestation.getAddress())))
		{
			throw new AlreadyExistingException("Firestation address already exists");
		}
		else {
			this.firestationRepository.addFirestation(firestation);
		}
		return firestation;
	}

	/**
	 * Update Firestation :
	 * Send parameter to the repository for checking
	 * 
	 * @return  - Firestation udated
	 */
	@Override
	public Firestation updateFirestation(String address, Firestation firestation)
	{
		return firestationRepository.updateFirestation(address, firestation);
	}

	/**
	 * Delete Firestations :
	 * Search if firestation existing by address and
	 * remove it from the list
	 * 
	 * @exception - {@link NotFoundException}
	 */
	@Override
	public void deleteFirestation(Firestation firestation)
	{
		if (firestationRepository.getAllFirestation().stream()
				.anyMatch(f -> f.getAddress().equals(firestation.getAddress())))
		{
			this.firestationRepository.deleteFirestation(firestation);
		}
		else {
		    throw new NotFoundException("Firestation does not exists");
		}
	}
}