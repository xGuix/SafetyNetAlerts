package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;

@Service
public class FirestationService implements IFirestationService {
	
	private static Logger logger = LogManager.getLogger("FirestationService");
	
	@Autowired
	private FirestationRepository firestationRepository;
    
	public List<Firestation> getAllFirestations(){
		return this.firestationRepository.getAllFirestation();
	}

	@Override
    public Firestation getAddressByNumber(int station) {
		logger.info("Firestation : {} is found", station);
    	return firestationRepository.getAddressByNumber(station);
    }
	
	@Override
	public Firestation addFirestation(Firestation firestation) {
		logger.info("Firestation : {} is found", firestation);
		return firestationRepository.addFirestation(firestation);
	}

	@Override
	public Firestation updateFirestation(Firestation firestation) {
		logger.info("New Firestion address : {} is update", firestation);
		return firestationRepository.updateAddressStation(firestation);
	}

	@Override
	public void deleteFirestation(Firestation firestation) {
		logger.info("Firestation : {} is delete", firestation);
		firestationRepository.deleteStation(firestation);
	}
}