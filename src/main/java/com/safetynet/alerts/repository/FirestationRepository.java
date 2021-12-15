package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepository implements IFirestationRepository {
	
	private static Logger logger = LogManager.getLogger("FirestationRepository");
    private List<Firestation> firestationList = new ArrayList<>();

	@Override
	public List<Firestation> getAllFirestation() {
		return this.firestationList;
	}
	
	@Override
	public Firestation addFirestation(Firestation firestation) {
		this.firestationList.add(firestation);
		logger.debug("Firestation {} is added to the list", firestation);
		return firestation;
	}	
	
	@Override
	public List<Firestation> getAddressByNumber(String station) {
		for (Firestation firestation: firestationList) {
			if(firestation.getStation().equals(station)) {
				logger.info("Person found : {} is sent" , station);
				return (List<Firestation>) firestation;
			}
		}
		logger.info("No firestation found ! Please check if typing error occurred");
		return null;
		
	}

	@Override
	public Firestation updateAddressStation(Firestation firestation) {
		if (firestationList.contains(getAddressByNumber(firestation.getStation()))) {
			firestationList.set(firestationList.indexOf(getAddressByNumber(firestation.getStation())), firestation);
			logger.debug("Firestation infos {} is updated", firestation);
			return firestation;
		}
		else {
			logger.debug("Firestation {} does not existe!", firestation);
			return firestation;
		}
	}

	@Override
	public void deleteStation(Firestation firestation) {
		if (firestationList.contains(getAddressByNumber(firestation.getStation()))) {
			firestationList.remove(firestation);
			logger.debug("Firestation {} is deleted from the list", firestation);
		}
		else {
			logger.debug("Firestation {} does not existe!", firestation);
		}
	}
}