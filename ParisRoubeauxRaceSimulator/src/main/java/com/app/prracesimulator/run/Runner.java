/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.prracesimulator.run;

import com.app.cyclistsimulatorpb.util.DataExtractor;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.Race;
import com.app.prracesimulator.util.RaceTimeTicker;

import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que ejecuta la simulación
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Runner {

    public static void main(String[] args) {
//        Race race = new Race();
//        System.out.println("CICLISTAS");
//        race.getRacers().forEach(System.out::println);
//        System.out.println("SEGMENTOS DE PAVÉ");
//        race.getPaveSegments().forEach(System.out::println);

        Timer timer = new Timer();
        RaceTimeTicker raceTimeTicker = RaceTimeTicker.getInstance();
        Cyclist c = new Cyclist(1,0,0,0,0,0,0,0,2,new Point2D.Double(0,1));
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                c.move(raceTimeTicker.getTime());
                raceTimeTicker.advance();
                System.out.println(c.getLocation());
            }
        };
        timer.schedule(timerTask,1, TimeUnit.SECONDS.toMillis(RaceTimeTicker.getDeltha()));

    }
}
