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
import java.util.Arrays;
import java.util.Collections;
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
	private RaceTimeTicker raceTimeTicker;
	private TimerTask timerTask;

	public Controller() {
		this.race = new Race();
		this.timer = new Timer();
		this.raceTimeTicker = RaceTimeTicker.getInstance();
		this.mainWindow = new MainWindow(this);
		this.mainWindow.setVisible(true);

		setUpTimerTask();
	}

	private void setUpTimerTask() {
		this.timerTask = new TimerTask() {

			@Override
			public void run() {
				updateWorld();
			}
		};
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

	/**
	 * metodo que se encarga de realizar la simulacion
	 */
	public void simulate() {

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				updateWorld();
			}
		};
		EditablePeriodTimerTask timerTask = new EditablePeriodTimerTask(task, () -> 10L);
		timerTask.run();
	}

	/**
	 * Método que se encarga de actualizar el mundo es decir la carrera y sus
	 * ciclistas, tanto en logica como view este se va a llamar en el hilo de
	 * timertask
	 */
	private void updateWorld() {

		if (!race.hasFinished()) {

			race.getRacers().forEach(cyclist -> {

				if (cyclist.getCyclistState().equals(CyclistState.RACING)) {

					cyclist.move();

					if (cyclist.getLocation().getX() >= RaceConstants.RACE_LENGTH
							&& !race.getRacersAtTheEnd().contains(cyclist)) {

						cyclist.setCyclistState(CyclistState.FINISHER);
						race.getRacersAtTheEnd().add(cyclist);

					}

					if (cyclist.getVelocityMS() < RaceConstants.MINIMUM_VELOCITY_THRESHOLD
							&& !race.getRacersAtTheEnd().contains(cyclist)) {

						cyclist.setCyclistState(CyclistState.DEQUALIFIED);
						race.getRacersAtTheEnd().add(cyclist);

					}
					// Aquí avanza al siguiente instante de tiempo
					raceTimeTicker.advance();
				}
			});

			// Actualiza las posiciones de los ciclistas en carrera de acuerdo a su posición
			this.race.updateCyclistRankingPositions();

			// Ajusta los valores de fatiga para todos los corredores
			race.adjustFatigueForAllRacers();

			// AJUSTAR DE ACUERDO A DISTANCIA RESPECTO AL SIGUIENTE MEJOR CICLISTA
			race.getRacers().forEach(race::adjustCyclistFatigueAccordingToClosenesToNextBestCyclist);

			System.out.println("---------- FATIGUE ADJUSTMENTS -------------");

//			race.getRacers().forEach(cyclist -> {
//				double unadjustedFatigue = cyclist.getFatigue();
//				double adjustedFatigue = race.getAdjustedFatigueAccordingToPhenomena(cyclist);
//				System.out.println("Cyclist " + cyclist.getId() + ": " + unadjustedFatigue + " -> " + adjustedFatigue);
//			});

			mainWindow.setRacers(race.getRacers());

		} else {

			this.race.getRacers().forEach(System.out::println);
			
			System.out.println("------------");

			try {
				Thread.sleep(100000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
