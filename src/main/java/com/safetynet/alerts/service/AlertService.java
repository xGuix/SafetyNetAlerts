package com.safetynet.alerts.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dto.ChildAlertDto;
import com.safetynet.alerts.dto.FireAlertDto;
import com.safetynet.alerts.dto.FirestationPersonAlertDto;
import com.safetynet.alerts.dto.FloodAlertDto;
import com.safetynet.alerts.dto.FirestationPersonsDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;

import java.util.ArrayList;

@Service
public class AlertService implements IAlertService
{
    @Autowired
    IPersonService personService;

    @Autowired
    IFirestationService firestationService;

    @Autowired
    IMedicalRecordService medicalRecordService;
	
	private static Logger logger = LogManager.getLogger("AlertService");
    
	/**
	 * Get persons list with children count for a station:
	 * 
	 * Search firestation with N°Station and
	 * get list of person at station addresses
	 * then count children.
	 * 
	 * @param station The station number to match
	 * @return List of Person with children count
	 */
	@Override
	public FirestationPersonAlertDto getPersonsListWithChildrenNumberForStation(String station)
	{
        int childrenNumber = 0;
        int adultsNumber = 0;
        List<String> fAddresses = firestationService.getOnlyAddressesFor(station);
        List<FirestationPersonsDto> personListAtAddress = new ArrayList<>();

        for(Person person : personService.getAllPersons())
        {
            if (fAddresses.toString().contains(person.getAddress()))
            {
            	personListAtAddress.add(new FirestationPersonsDto(person.getFirstName(),person.getLastName(),
            			person.getAddress(),person.getPhone()));
            	
                if (medicalRecordService.getHowOld(person.getFirstName(), person.getLastName()) < 18)
                {
                	childrenNumber ++;
                    logger.info("Add {} children to count", childrenNumber);
                }
                else if(medicalRecordService.getHowOld(person.getFirstName(), person.getLastName()) > 18)
                {
                	adultsNumber ++;
                    logger.info("Add {} adults to count", adultsNumber);
                }
            }
        }
        logger.info("Persons with count of children covered by station {}",station);
        return new FirestationPersonAlertDto(personListAtAddress, adultsNumber,childrenNumber);		
	}

	/**
	 * Get children with their family list for an address:
	 * 
	 * Search all children under 18 years old
	 * get familly list with lastName
	 * then get children list with their parents.
	 * 
	 * @param address The address to match
	 * @return List of children with family
	 */
	@Override
	public List<ChildAlertDto> getChildrenWithFamilyListAtAddress(String address)
	{
		List<ChildAlertDto> listOfChildrenWithFamily = new ArrayList<>();
		List<FirestationPersonsDto> listOfAdult = new ArrayList<>();

		for(Person person : personService.getAllPersons())
		{ 
			if(person.getAddress().equals(address))
			{
				if(medicalRecordService.getHowOld(person.getFirstName(), person.getLastName()) < 18)
				{
					listOfChildrenWithFamily.add(new ChildAlertDto(person.getFirstName(),person.getLastName(),
							medicalRecordService.getHowOld(person.getFirstName(),person.getLastName()), null));
				} 
				else
				{
					listOfAdult.add(new FirestationPersonsDto(person.getFirstName(),person.getLastName(),
							person.getAddress(),person.getPhone()));
				}
			}
		}
		listOfChildrenWithFamily.forEach(child -> child.setFamilyList(listOfAdult));
		logger.info("List of children with their family at {}",address);
		return listOfChildrenWithFamily;
	}

	/**
	 * Get phone persons list for a Station:
	 * 
	 * Search persons with addresses of station and
	 * get phone numbers list of person at addresses
	 * 
	 * @param station The station number to match
	 * @return list of phone number
	 */
	@Override
	public Set<String> getPhoneNumberOfStation(String station)
	{
		List<String> addresses = firestationService.getOnlyAddressesFor(station);
		logger.info("Get list of phone number for station N°{}",station);
		return personService.getAllPersons().stream()
				.filter(p -> addresses.toString().contains(p.getAddress()))
				.map(Person::getPhone)
				.collect(Collectors.toSet());
	}

	/**
	 * Get persons list and the firestation for an address:
	 * 
	 * Search all persons with address and
	 * get list of persons with firestation in charge
	 * 
	 * @param address The address to match
	 * @return List of personInfo with the firestation
	 */
	@Override
	public FireAlertDto getPersonsListAndFirestationForAddress(String address)
	{	
		Firestation firestation = firestationService.getOneFirestation(address);
		List<PersonInfoDto> personInfoListAtAddress = new ArrayList<>();
			
		for(Person person : personService.getAllPersons())
		{ 
			if(person.getAddress().equals(address))
			{
				personInfoListAtAddress.add(new PersonInfoDto(person.getFirstName(),person.getLastName(),
						medicalRecordService.getHowOld(person.getFirstName(),person.getLastName()),person.getPhone(),"not disclosed",
						medicalRecordService.getMedicalRecordByName(person.getFirstName(),person.getLastName()).getMedication(),
						medicalRecordService.getMedicalRecordByName(person.getFirstName(),person.getLastName()).getAllergie()));
			}
		}
		logger.info("List of person info with the firestation in charge of: {}",address);
		return new FireAlertDto(firestation, personInfoListAtAddress);
	}

	/**
	 * Get home family list for a station:
	 * 
	 * Search all persons by address and
	 * get list of home family sorted by address 
	 * 
	 * @param station The station number to match
	 * @return list of the Address and all HomeFamily
	 */
	@Override
	public List<FloodAlertDto> getHomeFamilyforStation (String station)
	{
		List<FloodAlertDto> homeFamilliesList = new ArrayList<>(); 	
		List<String> addresses = firestationService.getOnlyAddressesFor(station);
		
		for (String address : addresses)
		{
			List<PersonInfoDto> homeFamily = new ArrayList<>();
			
			for (Person person : personService.getAllPersons())
			{		
				PersonInfoDto personToMatch = new PersonInfoDto(person.getFirstName(),person.getLastName(),
						medicalRecordService.getHowOld(person.getFirstName(),person.getLastName()),"not disclosed",person.getEmail(),
						medicalRecordService.getMedicalRecordByName(person.getFirstName(),person.getLastName()).getMedication(),
						medicalRecordService.getMedicalRecordByName(person.getFirstName(),person.getLastName()).getAllergie());
				
				if (address.equals(person.getAddress()))
				{		
					homeFamily.add(personToMatch);
				}	
			}		
			homeFamilliesList.add(new FloodAlertDto(address,homeFamily));
		}
		logger.info("Get list of famillies with address covered by station");
		return homeFamilliesList;
	}
	
	/**
	 * Get all persons infos with a name:
	 * 
	 * Search all persons with name and
	 * get list of persons if multiple answers
	 * 
	 * @param firstName First name to match
	 * @param lastName Last name to match
	 * @return list of person in PersonInfo format
	 */
	@Override
	public List<PersonInfoDto> getPersonInfo(String firstName, String lastName)
	{
		List<PersonInfoDto> personInfoList= new ArrayList<>();
		for(Person person : personService.getAllPersons())
		{ 
			if(person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
			{
				personInfoList.add(new PersonInfoDto(person.getFirstName(),person.getLastName(),
						medicalRecordService.getHowOld(person.getFirstName(),person.getLastName()),person.getPhone(),person.getEmail(),
						medicalRecordService.getMedicalRecordByName(person.getFirstName(),person.getLastName()).getMedication(),
						medicalRecordService.getMedicalRecordByName(person.getFirstName(),person.getLastName()).getAllergie()));
			}
		}
		logger.info("Get all person infos of {} {}", firstName,lastName);
		return personInfoList;
	}

	/**
	 * Get emails list for a City:
	 * 
	 * Search persons with the city and
	 * get emails list of person for all city
	 * 
	 * @param city City to match
	 * @return list of emails for all city
	 */
	@Override
	public Set<String> getEmailsListByCity(String city)
	{
		logger.info("Get all person emails list of {}",city);
		return personService.getAllPersons().stream()
				.filter(p -> p.getCity().equals(city))
				.map(Person::getEmail)
				.collect(Collectors.toSet());
	}
}