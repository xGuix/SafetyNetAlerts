package com.safetynet.alerts.model;

import java.util.Objects;

import groovy.transform.Generated;

public class Firestation
{
	private String address;
	private String station;
	
	public Firestation(String address, String station)
	{
		this.address = address;
		this.station = station;
	}
	
	public Firestation()
	{
		// use empty method
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getStation()
	{
		return station;
	}
	
	public void setStation(String station)
	{
		this.station = station;
	}
	
	@Generated
	@Override
	public String toString() {
		return "Firestation [address=" + address + ", station=" + station + "]";
	}
	
	@Generated
	@Override
	public int hashCode() {
		return Objects.hash(address, station);
	}

	@Generated
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Firestation other = (Firestation) obj;
		return Objects.equals(address, other.address) &&
				Objects.equals(station, other.station);
	}
}