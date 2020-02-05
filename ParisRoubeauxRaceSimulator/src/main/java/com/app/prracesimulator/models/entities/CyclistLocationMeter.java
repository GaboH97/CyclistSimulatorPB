package com.app.cyclistsimulatorpb.models.entities;

import java.util.ArrayList;

public class CyclistLocationMeter {

	/**
	 * clase que determina por cada metro de los 258000 kms de la carrera 
	 * la ubicacion de todos los ciclistas... para cuando se llegue al metro 258000
	 * el ganador de la carrera sera el que tenga el valor de ubicacion en el peloton
	 * 1
	 */
	private int meter;
	private ArrayList<CyclistLocation> cyclistsLocation;
	
	public int getMeter() {
		return meter;
	}
	public void setMeter(int meter) {
		this.meter = meter;
	}
	public ArrayList<CyclistLocation> getCyclistsLocation() {
		return cyclistsLocation;
	}
	public void setCyclistsLocation(ArrayList<CyclistLocation> cyclistsLocation) {
		this.cyclistsLocation = cyclistsLocation;
	}
	public CyclistLocationMeter(int meter, ArrayList<CyclistLocation> cyclistsLocation) {
		super();
		this.meter = meter;
		this.cyclistsLocation = cyclistsLocation;
	}
	
	
	
}
