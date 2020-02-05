package com.app.cyclistsimulatorpb.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * METODO CONSTRUCTOR DEL CICLISTA basicamente se deben asignar los valores
     * de forma aleatoria para cada uno de los atritbutos esto teniendo en cuenta
     * que 50 sera la media de valores de fitness y fatiga entre los ciclistas y
     * para los valores de potencia se tendran en cuenta las tablas de watts por
     * kilo segun el nivel world class y excepcional.
     */

}
