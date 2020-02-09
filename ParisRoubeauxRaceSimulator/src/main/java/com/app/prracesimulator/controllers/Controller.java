package com.app.prracesimulator.controllers;

import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;
import com.app.prracesimulator.models.entities.Race;
import com.app.prracesimulator.models.entities.RaceConstants;
import com.app.prracesimulator.util.EditablePeriodTimerTask;
import com.app.prracesimulator.util.RaceTimeTicker;
import com.app.prracesimulator.views.MainWindow;

import lombok.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : Gabriel Huertas <gabriel970826@gmail.com> Date: 8/02/2020 Time:
 *         2:25 p. m.
 */
@Data
public class Controller implements ActionListener {

	private Timer timer;
	private Race race;
	private MainWindow mainWindow;
	RaceTimeTicker raceTimeTicker;

	public Controller() {
		this.race = new Race();
		this.mainWindow = new MainWindow(this);
		this.timer = new Timer();
		this.raceTimeTicker = RaceTimeTicker.getInstance();
		this.mainWindow.setVisible(true);
	}

	public void simulate() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (!race.hasFinished()) {
					race.getRacers().forEach(cyclist -> {
						if (cyclist.getCyclistState().equals(CyclistState.RACING)) {
	                		cyclist.setVelocityMS(cyclist.getVelocityAccordingFormKmH()/3.6);//se divide en 3.6 pues la velocidad esta en km/h y se necesita en m/s
	                		cyclist.move();
	                		System.out.println(cyclist);
	                		if (cyclist.getLocation().getX()>= RaceConstants.RACE_LENGTH && !race.getRacersAtTheEnd().contains(cyclist)) {
	                			cyclist.setCyclistState(CyclistState.FINISHER);
	                			race.getRacersAtTheEnd().add(cyclist);
							}
	                		if (cyclist.getVelocityMS()<1 && !race.getRacersAtTheEnd().contains(cyclist)) {
	                			cyclist.setCyclistState(CyclistState.DEQUALIFIED);
	                			race.getRacersAtTheEnd().add(cyclist);
							}
	                        raceTimeTicker.advance();
	                        mainWindow.setRacers(race.getRacers());
	            		}
					});
//                    System.out.println("-------------------");
				}
			}
		};
		// EL PARÁMETRO QUE ENTRA EN EL CONSTRUCTUR INDICA LA VELOCIDAD CON LA QUE SE VA
		// EJECUTAR EL PROGRAMA
		// PARA ESTE CASO ES DE 500L
		EditablePeriodTimerTask timerTask = new EditablePeriodTimerTask(task, () -> 500L);
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
