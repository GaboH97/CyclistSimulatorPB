package com.app.prracesimulator.controllers;

import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistLocation;
import com.app.prracesimulator.models.entities.Race;
import com.app.prracesimulator.util.EditablePeriodTimerTask;
import com.app.prracesimulator.util.RaceTimeTicker;
import com.app.prracesimulator.views.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : Gabriel Huertas <gabriel970826@gmail.com>
 * Date: 8/02/2020
 * Time: 2:25 p. m.
 */
public class Controller implements ActionListener {

    private Timer timer;
    private Race race;
    private MainWindow mainWindow;
    RaceTimeTicker raceTimeTicker;

    public Controller(){
        this.race = new Race();
        this.mainWindow = new MainWindow();
        this.timer = new Timer();
        this.raceTimeTicker = RaceTimeTicker.getInstance();
        this.simulate();
    }

    public void simulate() {
    	TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	for (CyclistLocation cyclistLocation : race.getLocationsInPeloton()) {
            		Cyclist c = cyclistLocation.getCyclist();
            		System.out.println(c.toString());

            		c.setVelocity(c.getVelocityAccordingForm()/3.6);//se divide en 3.6 pues la velocidad esta en km/h y se necesita en m/s
                    c.move();
                    raceTimeTicker.advance();
//                    System.out.println(c.getLocation());
                    System.out.println("-------------------");
				}
            }
        };
        //EL PARÁMETRO QUE ENTRA EN EL CONSTRUCTUR INDICA LA VELOCIDAD CON LA QUE SE VA EJECUTAR EL PROGRAMA
        //PARA ESTE CASO ES DE 500L
        EditablePeriodTimerTask timerTask = new EditablePeriodTimerTask(task, () ->500L);
        timerTask.run();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Actions.valueOf(e.getActionCommand())) {
            case SIMULATE:
                simulate();
                break;
            default:
                break;
        }
    }
}
