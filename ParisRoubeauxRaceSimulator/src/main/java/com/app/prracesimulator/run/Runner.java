/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.prracesimulator.run;

import com.app.cyclistsimulatorpb.util.DataExtractor;
import com.app.prracesimulator.models.entities.Race;

/**
 * Clase principal que ejecuta la simulación
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Runner {

    public static void main(String[] args) {
        Race race = new Race();
        System.out.println("CICLISTAS");
        race.getRacers().forEach(System.out::println);
        System.out.println("SEGMENTOS DE PAVÉ");
        race.getPaveSegments().forEach(System.out::println);

    }
}
