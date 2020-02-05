package com.app.cyclistsimulatorpb.models.entities;

/**
 * determina la ubicacion de un ciclista tando del peloton como la distancia que hay 
 * entre el y el siguiente mejor en la clasificacion
 * @author jacr
 *
 */
public class CyclistLocation implements Comparable<CyclistLocation>{

	/**
	 * es importante aclarar que cada vez que se haga una instancia de esta clase
	 * se debe pasar como parametro al ciclista pero utilizando el metodo .clone()
	 * esto con el fin de poder editar los datos del ciclista sin perder los datos
	 * anteriores que han ido teniendo durante la carrera
	 */
	private Cyclist cyclist;
	private int locationInPeloton;
	private int clossenesToBestNextInMeters;
	
	public Cyclist getCyclist() {
		return cyclist;
	}
	public void setCyclist(Cyclist cyclist) {
		this.cyclist = cyclist;
	}
	public int getLocationInPeloton() {
		return locationInPeloton;
	}
	public void setLocationInPeloton(int locationInPeloton) {
		this.locationInPeloton = locationInPeloton;
	}
	public int getClossenesToBestNextInMeters() {
		return clossenesToBestNextInMeters;
	}
	public void setClossenesToBestNextInMeters(int clossenesToBestNextInMeters) {
		this.clossenesToBestNextInMeters = clossenesToBestNextInMeters;
	}
	public CyclistLocation(Cyclist cyclist, int locationInPeloton, int clossenesToBestNextInMeters) {
		super();
		this.cyclist = cyclist;
		this.locationInPeloton = locationInPeloton;
		this.clossenesToBestNextInMeters = clossenesToBestNextInMeters;
	}
	@Override
	public int compareTo(CyclistLocation o) {
		if(this.locationInPeloton > o.getLocationInPeloton()) {
			return 1;
		}else {
			return 0;
		}
	}
}
