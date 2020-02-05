package com.app.prracesimulator.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * determina la ubicacion de un ciclista tando del peloton como la distancia que hay 
 * entre el y el siguiente mejor en la clasificacion
 * @author jacr
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

	@Override
	public int compareTo(CyclistLocation o) {
		if(this.locationInPeloton > o.getLocationInPeloton()) {
			return 1;
		}else {
			return 0;
		}
	}
}
