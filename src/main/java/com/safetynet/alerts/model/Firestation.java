package com.safetynet.alerts.model;

public class Firestation {
	
	private String address;
	private int station;
	
	public Firestation(String address, int station) {
		this.address = address;
		this.station = station;
	}
	
	public Firestation() {
		// use empty method
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getStation() {
		return station;
	}
	
	public void setStation(int station) {
		this.station = station;
	}
}