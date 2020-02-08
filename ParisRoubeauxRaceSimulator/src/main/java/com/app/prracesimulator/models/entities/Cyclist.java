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
public class Cyclist {
    
    
    public static int MIN_WEIGHT = 56; //En Kilogramos
    public static int MAX_WEIGHT = 80; //En Kilogramos
    public static int MIN_FITNESS_FACTOR = 1;
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
     * En m/s
     */
    private double velocity;

    private Point2D.Double location;

    /**
     * METODO CONSTRUCTOR DEL CICLISTA basicamente se deben asignar los valores
     * de forma aleatoria para cada uno de los atritbutos esto teniendo en cuenta
     * que 50 sera la media de valores de fitness y fatiga entre los ciclistas y
     * para los valores de potencia se tendran en cuenta las tablas de watts por
     * kilo segun el nivel world class y excepcional.
     */
    public void move(Long currentTime){
        this.location.setLocation(
                updateLocationOnXAxis(currentTime),
                this.location.getY());
    }

    /**
     * Para movimiento rectilíneo uniforme
     * @param currentTime
     * @return
     */
    public double updateLocationOnXAxis(Long currentTime){
        return velocity*currentTime;
    }
}
