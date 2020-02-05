package com.app.prracesimulator.models.entities;

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
public class PaveSection {

	private int id;
	private int start;
	private int length; //In meters
	private int difficulty;
        
}
