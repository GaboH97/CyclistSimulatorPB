package com.app.prracesimulator.views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import com.app.prracesimulator.controllers.Actions;
import com.app.prracesimulator.controllers.Controller;

public class SimulationVariablesDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnIniciar;

	public SimulationVariablesDialog(Controller controller) {
		setSize(400, 400);
		setLocationRelativeTo(null);
		initComponents();
		setActions(controller);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		GridSystem gridSystem = new GridSystem(this);
		gridSystem.addExternalBorder(10, 0, 10, 0);
		JLabel longitud = new JLabel("Longitud: ");
		JSpinner jSpLongitud = new JSpinner();
		add(longitud, gridSystem.insertComponent(1, 2, 6, 1));
		add(jSpLongitud, gridSystem.insertComponent(1, 5, 5, 1));

		JLabel viento = new JLabel("Viento: ");
		JSpinner jSpViento = new JSpinner();
		add(viento, gridSystem.insertComponent(2, 2, 6, 1));
		add(jSpViento, gridSystem.insertComponent(2, 5, 5, 1));

		JLabel numCiclistas = new JLabel("Número Ciclistas: ");
		JSpinner jSpNumCiclistas = new JSpinner();
		add(numCiclistas, gridSystem.insertComponent(3, 2, 6, 1));
		add(jSpNumCiclistas, gridSystem.insertComponent(3, 5, 5, 1));

		JLabel fitness = new JLabel("Fitness: ");
		JSpinner jSpFitness = new JSpinner();
		add(fitness, gridSystem.insertComponent(4, 2, 6, 1));
		add(jSpFitness, gridSystem.insertComponent(4, 5, 5, 1));

		JLabel fatiga = new JLabel("Fatiga: ");
		JSpinner jSpFatiga = new JSpinner();
		add(fatiga, gridSystem.insertComponent(5, 2, 6, 1));
		add(jSpFatiga, gridSystem.insertComponent(5, 5, 5, 1));

		JLabel cansancio = new JLabel("Cansancio: ");
		JSpinner jSpCansancio = new JSpinner();
		add(cansancio, gridSystem.insertComponent(6, 2, 6, 1));
		add(jSpCansancio, gridSystem.insertComponent(6, 5, 5, 1));

		JLabel descanso = new JLabel("Descanso: ");
		JSpinner jSpDescanso = new JSpinner();
		add(descanso, gridSystem.insertComponent(7, 2, 6, 1));
		add(jSpDescanso, gridSystem.insertComponent(7, 5, 5, 1));

		btnIniciar = new JButton("Empezar");
		add(btnIniciar, gridSystem.insertComponent(8, 4, 3, 1));
	}

	/**
	 * 
	 * @param controller
	 */
	public void setActions(Controller controller) {
		btnIniciar.setActionCommand(Actions.SIMULATE.name());
		btnIniciar.addActionListener(controller);
	}
}
