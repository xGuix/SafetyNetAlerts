package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class FirestationService implements IFirestationService {
	
	@Autowired
	private FirestationRepository firestationRepository;
    
	public List<Firestation> getAllFirestations(){
		return this.firestationRepository.getAllFirestation();
	}
}