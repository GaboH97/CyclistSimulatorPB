package com.app.prracesimulator.models.entities;

/**
 *
 */
public class RaceConstants {

	/**
	 * Longitud de la carrera en metros (Paris-Roubaix Edicion 2019)
	 */
	public static final int RACE_LENGTH = 250;//257000
	public static final int HEAD_WIND = 0;
	/**
	 * Cambiarlo a 3 ciclistas
	 */
	public static int NUMBER_OF_CYCLISTS = 5;

	// TODO completar
	public static double WC_MIN_5S = 22.14;
	public static double WC_MAX_5S = 24.04;
	public static double WC_MIN_1M = 20.23;
	public static double WC_MAX_1M = 21;
	public static double WC_MIN_5M = 2;
	public static double WC_MAX_5M = 4;
	public static double WC_MIN_FT = 2;
	public static double WC_MAX_FT = 14;
	public static double EX_MIN_5S = 22.14;
	public static double EX_MAX_5S = 24.04;
	public static double EX_MIN_1M = 1;
	public static double EX_MAX_1M = 144;
	public static double EX_MIN_5M = 1;
	public static double EX_MAX_5M = 14;
	public static double EX_MIN_FT = 1;
	public static double EX_MAX_FT = 14;
	// TODO completar pesos de los ciclistas segun su tipo
	public static double SP_WEIGHT_MIN = 1;
	public static double SP_WEIGHT_MAX = 14;
	public static double GP_WEIGHT_MIN = 1;
	public static double GP_WEIGHT_MAX = 14;
	public static double GT_WEIGHT_MIN = 1;
	public static double GT_WEIGHT_MAX = 14;

	public static int MIN_FITNESS = 1; // Unidad escalar
	public static int MAX_FITNESS = 100;
	public static int MIN_FATIGUE_INIT = 1;
	public static int MAX_FATIGUE_INIT = 10;
	
    public static double TIREDNESS_FACTOR = 7200;//este factor no es multiplicativo sino de division
    public static double RESTENESS_FACTOR = 1800;//este factor no es multiplicativo sino de division
}
