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
	
	public List<Firestation> getAllFirestations()
	{
		return this.firestationRepository.getAllFirestation();
	}

	@Override
    public List<Firestation> getFirestationsFor(String station)
	{
		logger.info("Firestation N°{} Check matches.", station);
		return firestationRepository.getAllFirestation().stream()
				.filter(f -> f.getStation().equals(station))
				.collect(Collectors.toList());
	}
	
    public List<String> getOnlyAddressesFor(String station)
	{
		logger.info("Firestation N°{} Check matches.", station);
		return firestationRepository.getAllFirestation().stream()
				.filter(f -> f.getStation().equals(station))
				.map(Firestation::getAddress)
				.collect(Collectors.toList());
	}
	
	@Override
	public Firestation getOneFirestation(String address, String station)
	{
		logger.info("Searching match address for Firestation N°{} with '{}'", station, address);
		return firestationRepository.getAllFirestation().stream()
	    		.filter(f -> f.getAddress().equals(address))
	    		.findAny().orElseThrow(() -> new NotFoundException("Address does not exists"));
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation)
	{
		if (firestationRepository.getAllFirestation().stream()
				.anyMatch(f -> f.getAddress().equals(firestation.getAddress())))
		{
			throw new AlreadyExistingException("Firestation address already exists");
		}
		else {
			firestationRepository.addFirestation(firestation);
		}
		return firestation;
	}

	@Override
	public Firestation updateFirestation(String address, Firestation firestation)
	{
		return firestationRepository.updateFirestation(address, firestation);
	}

	@Override
	public void deleteFirestation(Firestation firestation)
	{
		if (firestationRepository.getAllFirestation().stream()
				.anyMatch(f -> f.getAddress().equals(firestation.getAddress())))
		{
		firestationRepository.deleteFirestation(firestation);
		}
		else {
		    throw new NotFoundException("Firestation does not exists");
		}
	}
}