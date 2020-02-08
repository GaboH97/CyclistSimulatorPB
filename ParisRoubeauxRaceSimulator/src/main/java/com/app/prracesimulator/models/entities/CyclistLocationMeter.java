package com.app.prracesimulator.models.entities;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CyclistLocationMeter {

	/**
	 * clase que determina por cada metro de los 258000m de la carrera
	 * la ubicacion de todos los ciclistas... para cuando se llegue al metro 258000
	 * el ganador de la carrera sera el que tenga el valor de ubicacion en el peloton
	 * 1
	 */
	private int meter;
	private ArrayList<CyclistLocation> cyclistsLocation;	
}
