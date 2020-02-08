package com.app.prracesimulator.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * clase que determina los atributos del ciclista
 *
 * @author jacr
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cyclist implements Movable{

    public static int MIN_WEIGHT = 56; //En Kilogramos
    public static int MAX_WEIGHT = 80; //En Kilogramos
    public static int MIN_FITNESS_FACTOR = 1; //Unidad escalar
    public static int MAX_FITNESS_FACTOR = 100;
    public static int MIN_FATIGUE_FACTOR = 1;
    public static int MAX_FATIGUE_FACTOR = 100;
    
    private int id;
    private double fiveSecsPower;
    private double oneMinPower;
    private double fiveMinPower;
    private double oneHourPower;

    /**
     * Peso en kilogramos
     */
    private double weight;
    /**
     * Factor comprendido entre 1.0 y 100.0
     */
    private double fitnessFactor;
    /**
     * Factor comprendido entre 1.0 y 100.0
     */
    private double fatigueFactor;

    /**
     * Ubicación del ciclista en dos dimensiones (x,y)
     */
    private Point2D.Double location;

    /**
     * En m/s
     */
    private double velocity;

    /**
     * En m/s^2
     */
    private double acceleration;

    /**
     * Método que desplaza la
     * @param currentTime
     */
    @Override
    public void move(long currentTime){
        this.location.setLocation(
                updateLocationOnXAxis(currentTime),
                this.location.getY());
    }

    /**
     * Para movimiento rectilíneo uniformemente acelerado
     * @param currentTime
     * @return
     */

    public double updateLocationOnXAxis(Long currentTime){
        return velocity*currentTime;
//        return velocity*currentTime + 0.5 * acceleration*Math.pow(currentTime,2);
    }

    /**
     *
     * @param currentTime
     * @return
     */
    public double updateVelocity(long currentTime){
        if(velocity == 0){
            return  acceleration*currentTime;
        }
        return velocity + acceleration*currentTime;
    }
}
