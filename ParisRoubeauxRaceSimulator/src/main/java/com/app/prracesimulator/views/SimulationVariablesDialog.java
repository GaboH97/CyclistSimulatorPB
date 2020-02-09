package com.app.prracesimulator.views;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class SimulationVariablesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public SimulationVariablesDialog(MainWindow mainWindow) {
		super(mainWindow);
		setSize(500, 500);
		setLocationRelativeTo(null);
		initComponents();
		setVisible(true);
	}
	
	private void initComponents() {
		GridSystem gridSystem = new GridSystem(this);
		gridSystem.addExternalBorder(10,0,10,0);
		JLabel longitud = new JLabel("Longitud: ");
		JSpinner jSpLongitud = new JSpinner();
		add(longitud, gridSystem.insertComponent(1, 2, 6, 1));
		add(jSpLongitud, gridSystem.insertComponent(1, 5, 5, 1));
		
		JLabel viento = new JLabel("Viento: ");
		JSpinner jSpViento = new JSpinner();
		add(viento, gridSystem.insertComponent(2, 1, 4, 1));
		add(jSpViento, gridSystem.insertComponent(2, 3, 8, 1));
		
		JLabel numCiclistas = new JLabel("Número Ciclistas: ");
		JSpinner jSpNumCiclistas = new JSpinner();
		add(numCiclistas, gridSystem.insertComponent(3, 1, 4, 1));
		add(jSpNumCiclistas, gridSystem.insertComponent(3, 3, 8, 1));
		
		JLabel fitness = new JLabel("Fitness: ");
		JSpinner jSpFitness = new JSpinner();
		add(fitness, gridSystem.insertComponent(4, 1, 4, 1));
		add(jSpFitness, gridSystem.insertComponent(4, 3, 8, 1));
		
		JLabel fatiga = new JLabel("Fatiga: ");
		JSpinner jSpFatiga = new JSpinner();
		add(fatiga, gridSystem.insertComponent(5, 1, 4, 1));
		add(jSpFatiga, gridSystem.insertComponent(5, 3, 8, 1));
		
		JLabel cansancio = new JLabel("Cansancio: ");
		JSpinner jSpCansancio = new JSpinner();
		add(cansancio, gridSystem.insertComponent(6, 1, 4, 1));
		add(jSpCansancio, gridSystem.insertComponent(6, 3, 8, 1));
		
		JLabel descanso = new JLabel("Descanso: ");
		JSpinner jSpDescanso = new JSpinner();
		add(descanso, gridSystem.insertComponent(7, 1, 4, 1));
		add(jSpDescanso, gridSystem.insertComponent(7, 3, 8, 1));
		
		JButton btnIniciar = new JButton("Empezar");
		add(btnIniciar, gridSystem.insertComponent(8, 4, 3, 1));
	}
}
