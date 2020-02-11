package com.app.prracesimulator.models.entities;

/**
 * Clase que define las constantes de la simulación, tanto para los ciclistas, 
 * @author Lenovo
 *
 */
public class RaceConstants {
	
	
	
	/**
	 * Longitud de la carrera en metros (Paris-Roubaix Edicion 2019)
	 */
	public static int RACE_LENGTH = 700;
	public static int HEAD_WIND = 0;
	/**
	 * Cambiarlo a 3 ciclistas
	 */
	public static int NUMBER_OF_CYCLISTS = 20;

	//------------------- MPO (MAXIMAL POWER OUTPUT) VALUES -------------------------
	
	public static double WC_MIN_5S = 22.14;
	public static double WC_MAX_5S = 24.04;
	public static double WC_MIN_1M = 10.58;
	public static double WC_MAX_1M = 11.50;
	public static double WC_MIN_5M = 6.88;
	public static double WC_MAX_5M = 7.60;
	public static double WC_MIN_FT = 5.78;
	public static double WC_MAX_FT = 6.40;
	public static double EX_MIN_5S = 20.51;
	public static double EX_MAX_5S = 22.41;
	public static double EX_MIN_1M = 10.01;
	public static double EX_MAX_1M = 10.81;
	public static double EX_MIN_5M = 6.26;
	public static double EX_MAX_5M = 6.98;
	public static double EX_MIN_FT = 5.24;
	public static double EX_MAX_FT = 5.87;
	
	//---------------- PESOS DE CICLISTAS -----------------------
	
	public static double SP_WEIGHT_MIN = 56;
	public static double SP_WEIGHT_MAX = 67;
	public static double GP_WEIGHT_MIN = 67;
	public static double GP_WEIGHT_MAX = 78;
	public static double GT_WEIGHT_MIN = 78;
	public static double GT_WEIGHT_MAX = 90;

	//---------------------- FACTORES ----------------------
	public static int MIN_FITNESS = 1; // Unidad escalar
	public static int MAX_FITNESS = 100; // Unidad escalar
	public static int MIN_FATIGUE_INIT = 1; // Unidad escalar
	public static int MAX_FATIGUE_INIT = 10; // Unidad escalar
	
    public static double TIREDNESS_FACTOR = 7200;//este factor no es multiplicativo sino de division
    public static double RESTENESS_FACTOR = 1800;//este factor no es multiplicativo sino de division
    
    
    //------------------ FACTOR DE ESCALA DE FATIGA DE ACUERDO A LA DIFICULTAD DEL PAVÉ -------------------
    
    public static double FATIGUE_SCALE_FACTOR_1S_PAVE = 1.5;
    public static double FATIGUE_SCALE_FACTOR_2S_PAVE = 2;
    public static double FATIGUE_SCALE_FACTOR_3S_PAVE = 3;
    public static double FATIGUE_SCALE_FACTOR_4S_PAVE = 4;
    public static double FATIGUE_SCALE_FACTOR_5S_PAVE = 5;
   
    
    //Corresponde a la velocidad mínima que puede tener un ciclista de movimiento para no ser considerado como DESCALIFICADO
    
    public static double MINIMUM_VELOCITY_THRESHOLD = 1; //En m/S

}
