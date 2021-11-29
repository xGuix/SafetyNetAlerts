package com.safetynet.alerts.model;

public class Firestation {
	private String address;
	private int Station;
	
	public Firestation(String address, int station) {
		super();
		this.address = address;
		Station = station;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStation() {
		return Station;
	}
	public void setStation(int station) {
		Station = station;
	}
}