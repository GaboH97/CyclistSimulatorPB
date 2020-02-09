package com.app.prracesimulator.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.beans.Transient;
import java.util.Random;

/**
 * clase que determina los atributos del ciclista
 *
 * @author jacr
 *
 */
@Data
@AllArgsConstructor
public class Cyclist implements Movable{
    
    private int id;
    @ToString.Exclude 
    private double fiveSecsPower;
    @ToString.Exclude 
    private double oneMinPower;
    @ToString.Exclude 
    private double fiveMinPower;
    @ToString.Exclude 
    private double oneHourPower;

    /**
     * Peso en kilogramos
     */
    @ToString.Exclude 
    private double weight;
    /**
     * Factor comprendido entre 1.0 y 100.0
     */
    private double fitnessFactor;
    /**
     * Factor comprendido entre 1.0 y 100.0
     */
    private double fatigue;

    /**
     * Ubicación del ciclista en dos dimensiones (x,y)
     */
    private Point2D.Double location;

    /**
     * En m/s
     */
    @ToString.Exclude 
    private double velocityMS;

    /**
     * tipo de llegada, termino o dequalified
     */
    
    private CyclistState cyclistState;
    /**
     * Método que desplaza la
     * @param currentTime
     */
    @Override
    public void move(){
        this.location.setLocation(
                this.location.getX()+velocityMS,
                this.location.getY());
    }

	public Cyclist(int id, Random rand) {
		
		this.id = id;
		//asignacion perfil de poder-------------------------------------------------------------------------
        //TODO meter en ternario
        if (rand.nextBoolean()) {
        	fiveSecsPower = rand.doubles(RaceConstants.EX_MIN_5S,RaceConstants.EX_MAX_5S).findFirst().getAsDouble();
		}else {
			fiveSecsPower = rand.doubles(RaceConstants.WC_MIN_5S,RaceConstants.WC_MAX_5S).findFirst().getAsDouble();			
		} 
        if (rand.nextBoolean()) {
        	oneMinPower = rand.doubles(RaceConstants.EX_MIN_1M,RaceConstants.EX_MAX_1M).findFirst().getAsDouble();
		}else {
        	oneMinPower = rand.doubles(RaceConstants.WC_MIN_1M,RaceConstants.WC_MAX_1M).findFirst().getAsDouble();
		} 
        if (rand.nextBoolean()) {
        	fiveMinPower = rand.doubles(RaceConstants.EX_MIN_5M,RaceConstants.EX_MAX_5M).findFirst().getAsDouble();
		}else {
        	fiveMinPower = rand.doubles(RaceConstants.WC_MIN_5M,RaceConstants.WC_MAX_5M).findFirst().getAsDouble();
		} 
        if (rand.nextBoolean()) {
        	oneHourPower = rand.doubles(RaceConstants.EX_MIN_FT,RaceConstants.EX_MAX_FT).findFirst().getAsDouble();
		}else {
        	oneHourPower = rand.doubles(RaceConstants.WC_MIN_FT,RaceConstants.WC_MAX_FT).findFirst().getAsDouble();
		}  
        //asignacion peso ciclista sugun su tip--------------------------------------------
        double difMAxMin5s= RaceConstants.WC_MAX_5S -   RaceConstants.EX_MIN_5S;
        double difMAxMin1M= RaceConstants.WC_MAX_1M -   RaceConstants.EX_MIN_1M;
        double difMAxMin5M= RaceConstants.WC_MAX_5M -   RaceConstants.EX_MIN_5M;
        double difMAxMinFt= RaceConstants.WC_MAX_FT -   RaceConstants.EX_MIN_FT;
        
        double difCyclistMin5s= this.fiveSecsPower -   RaceConstants.EX_MIN_5S;
        double difCyclistMin1M= this.fiveSecsPower -   RaceConstants.EX_MIN_1M;
        double difCyclistMin5M = this.fiveSecsPower -   RaceConstants.EX_MIN_5M;
        double difCyclistMinFt = this.fiveSecsPower -   RaceConstants.EX_MIN_FT;
        
        double percentageCyclist5s= (difCyclistMin5s*100)/difMAxMin5s;
        double percentageCyclist1M= (difCyclistMin1M*100)/difMAxMin1M;
        double percentageCyclist5M = (difCyclistMin5M*100)/difMAxMin5M;
        double percentageCyclistFt = (difCyclistMinFt*100)/difMAxMinFt;
        
        if(percentageCyclist5s >=  percentageCyclist5M &&
        		percentageCyclist5s >=  percentageCyclistFt &&
        		percentageCyclist1M >=  percentageCyclist5M && 
        		percentageCyclist1M >=  percentageCyclistFt) {
        	//peso sprinter
        	this.weight = rand.doubles(RaceConstants.SP_WEIGHT_MIN,RaceConstants.SP_WEIGHT_MAX).findFirst().getAsDouble();
        }
        if (percentageCyclist5M >=  percentageCyclist5s &&
        		percentageCyclist5M >=  percentageCyclist1M &&
        		percentageCyclist5M >=  percentageCyclistFt ) {
			//peso de golpeador
        	this.weight = rand.doubles(RaceConstants.GP_WEIGHT_MIN,RaceConstants.GP_WEIGHT_MAX).findFirst().getAsDouble();
		}
        if (percentageCyclistFt >=  percentageCyclist5s &&
        		percentageCyclistFt >=  percentageCyclist1M &&
        				percentageCyclistFt >=  percentageCyclist5M) {
        	this.weight = rand.doubles(RaceConstants.GT_WEIGHT_MIN,RaceConstants.GT_WEIGHT_MAX).findFirst().getAsDouble();
		}
        
        //------------------------------------------------------------------
		this.fitnessFactor = rand.doubles(RaceConstants.MIN_FITNESS, (RaceConstants.MAX_FITNESS)).findFirst().getAsDouble();
		this.fatigue = rand.doubles(RaceConstants.MIN_FATIGUE_INIT, (RaceConstants.MAX_FATIGUE_INIT)).findFirst().getAsDouble();
		//TODO MIRAR ESTO
		this.velocityMS = 0;
		this.location = new Double(0, 0);
		this.cyclistState = CyclistState.RACING;
//		this.acceleration = acceleration;
	}
    
	
	public double getVelocityAccordingFormKmH() {
		double effortThatCanPerform = ((this.getFitnessFactor()-this.getFatigue())/2)+50;
//		System.out.println("effort" + effortThatCanPerform);
		double velocityAccordingFormKmH = Math.log10(effortThatCanPerform)*20+20;
//		System.out.println("velocityAccordingFormKMH " + velocityAccordingFormKmH);
		this.fatigue += velocityAccordingFormKmH/RaceConstants.TIREDNESS_FACTOR; 
//		System.out.println("fatigue" + this.fatigue);
		
		return velocityAccordingFormKmH;
	}
    
}
