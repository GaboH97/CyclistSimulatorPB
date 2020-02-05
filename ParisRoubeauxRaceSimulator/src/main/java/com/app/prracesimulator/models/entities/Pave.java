package com.app.cyclistsimulatorpb.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * clase que determina los segmentos de pave deacuerdo a la tabla de segemntos
 * @author jacr
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pave {

	private int id;
	private int startMeter;
	private int lenghtInMeters;
	private int dificulty;
        
}
