/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.prracesimulator.run;

import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.Race;
import com.app.prracesimulator.util.EditablePeriodTimerTask;
import com.app.prracesimulator.util.RaceTimeTicker;

import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

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

        Timer timer = new Timer();
        RaceTimeTicker raceTimeTicker = RaceTimeTicker.getInstance();
        Cyclist c = new Cyclist(1,0,0,0,0,0,0,2,new Point2D.Double(0,1),2,1);


        //DEFINE LA TAREA A REALIZAR

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                c.move(raceTimeTicker.getTime());
                raceTimeTicker.advance();
                System.out.println(c.getLocation());
            }
        };

        //EL PARÁMETRO QUE ENTRA EN EL CONSTRUCTUR INDICA LA VELOCIDAD CON LA QUE SE VA EJECUTAR EL PROGRAMA
        //PARA ESTE CASO ES DE 500L
        EditablePeriodTimerTask timerTask = new EditablePeriodTimerTask(task, () -> 500L);
        timerTask.run();

 //       timer.schedule(timerTask,1, TimeUnit.SECONDS.toMillis(RaceTimeTicker.getDeltha()));

    }
}
