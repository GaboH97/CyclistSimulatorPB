package com.app.prracesimulator.controllers;

import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;
import com.app.prracesimulator.models.entities.Race;
import com.app.prracesimulator.models.entities.RaceConstants;
import com.app.prracesimulator.util.EditablePeriodTimerTask;
import com.app.prracesimulator.util.RaceTimeTicker;
import com.app.prracesimulator.views.MainWindow;
import com.app.prracesimulator.views.SimulationVariablesDialog;

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
	private SimulationVariablesDialog simulationVariablesDialog;
	private RaceTimeTicker raceTimeTicker;

	public Controller() {
		this.race = new Race();
		this.timer = new Timer();
		this.raceTimeTicker = RaceTimeTicker.getInstance();
		this.simulationVariablesDialog = new SimulationVariablesDialog(this);
		this.mainWindow = new MainWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case SIMULATE:
			simulate();
			break;
		case CLOSE_MAIN_WINDOW:
			closeMainWindow();
			break;
		}
	}

	public void closeMainWindow() {
		this.simulationVariablesDialog.setVisible(true);
		this.mainWindow.setVisible(false);
	}

	/**
	 * metodo que se encarga de realizar la simulacion
	 */
	public void simulate() {
		this.mainWindow.setVisible(true);
		this.simulationVariablesDialog.setVisible(false);
		changeSettingsSimulation();
		this.race.getRacers().forEach(System.out::println);
		System.out.println("------------");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				updateWorld();
			}
		};
		// EL PARÁMETRO QUE ENTRA EN EL CONSTRUCTUR INDICA LA VELOCIDAD CON LA QUE SE VA
		// EJECUTAR EL PROGRAMA PARA ESTE CASO ES DE 500L
		EditablePeriodTimerTask timerTask = new EditablePeriodTimerTask(task, () -> 1000L);
		timerTask.run();
	}

	public void changeSettingsSimulation() {
		RaceConstants.RACE_LENGTH = (int) simulationVariablesDialog.getjSpLongitud().getValue();
		RaceConstants.HEAD_WIND = (int) simulationVariablesDialog.getjSpViento().getValue();
		RaceConstants.NUMBER_OF_CYCLISTS = (int) simulationVariablesDialog.getjSpNumCiclistas().getValue();
		RaceConstants.MIN_FITNESS = (int) simulationVariablesDialog.getjSpFitnessMin().getValue();
		RaceConstants.MAX_FITNESS = (int) simulationVariablesDialog.getjSpFitnessMax().getValue();
		RaceConstants.MIN_FATIGUE_INIT = (int) simulationVariablesDialog.getjSpFatigaMin().getValue();
		RaceConstants.MAX_FATIGUE_INIT = (int) simulationVariablesDialog.getjSpFatigaMax().getValue();
	}

	/**
	 * metodo que se encarga de actualizar el mundo es decir la carrera y sus
	 * ciclistas, tanto en logica como view este se va a llamar en el hilo de
	 * timertask
	 */
	private void updateWorld() {
		if (!race.hasFinished()) {
			race.getRacers().forEach(cyclist -> {
				if (cyclist.getCyclistState().equals(CyclistState.RACING)) {
					// se divide en 3.6 pues la velocidad esta en km/h y se necesita en m/s
					cyclist.setVelocityMS(cyclist.getVelocityAccordingFormKmH() / 3.6);
					cyclist.move();
					if (cyclist.getLocation().getX() >= RaceConstants.RACE_LENGTH
							&& !race.getRacersAtTheEnd().contains(cyclist)) {
						cyclist.setCyclistState(CyclistState.FINISHER);
						race.getRacersAtTheEnd().add(cyclist);
					}
					if (cyclist.getVelocityMS() < 1 && !race.getRacersAtTheEnd().contains(cyclist)) {
						cyclist.setCyclistState(CyclistState.DEQUALIFIED);
						race.getRacersAtTheEnd().add(cyclist);
					}
					raceTimeTicker.advance();
				}
			});
			this.race.updateCyclistRankingPositions();//
			// TODO: enviar lista actualizada
			for (Cyclist racer : race.getRacers()) {
				System.out.println(racer);
//				racer.setFatigue(racer.getFatigue()-(1/(5*race.getNextBestInMeters(racer.getId()))));
			}
			mainWindow.setRacers(race.getRacers());// se actualiza la view
		} else {
			this.race.getRacers().forEach(System.out::println);
			System.out.println("------------");
//			System.exit(0);;
		}
	}

}
