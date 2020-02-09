package com.app.prracesimulator.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import com.app.prracesimulator.controllers.Actions;
import com.app.prracesimulator.controllers.Controller;

public class SimulationVariablesDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnIniciar;
	private JSpinner jSpLongitud;
	private JSpinner jSpViento;
	private JSpinner jSpNumCiclistas;
	private JSpinner jSpFitness;
	private JSpinner jSpFatiga;
	private JSpinner jSpCansancio;
	private JSpinner jSpDescanso;

	public SimulationVariablesDialog(Controller controller) {
		designWindow();
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
		jSpLongitud = new JSpinner();
		add(longitud, gridSystem.insertComponent(1, 2, 6, 1));
		add(jSpLongitud, gridSystem.insertComponent(1, 5, 5, 1));

		JLabel viento = new JLabel("Viento: ");
		jSpViento = new JSpinner();
		add(viento, gridSystem.insertComponent(2, 2, 6, 1));
		add(jSpViento, gridSystem.insertComponent(2, 5, 5, 1));

		JLabel numCiclistas = new JLabel("Número Ciclistas: ");
		jSpNumCiclistas = new JSpinner();
		add(numCiclistas, gridSystem.insertComponent(3, 2, 6, 1));
		add(jSpNumCiclistas, gridSystem.insertComponent(3, 5, 5, 1));

		JLabel fitness = new JLabel("Fitness: ");
		jSpFitness = new JSpinner();
		add(fitness, gridSystem.insertComponent(4, 2, 6, 1));
		add(jSpFitness, gridSystem.insertComponent(4, 5, 5, 1));

		JLabel fatiga = new JLabel("Fatiga: ");
		jSpFatiga = new JSpinner();
		add(fatiga, gridSystem.insertComponent(5, 2, 6, 1));
		add(jSpFatiga, gridSystem.insertComponent(5, 5, 5, 1));

		JLabel cansancio = new JLabel("Cansancio: ");
		jSpCansancio = new JSpinner();
		add(cansancio, gridSystem.insertComponent(6, 2, 6, 1));
		add(jSpCansancio, gridSystem.insertComponent(6, 5, 5, 1));

		JLabel descanso = new JLabel("Descanso: ");
		jSpDescanso = new JSpinner();
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

	private static void designWindow() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SimulationVariablesDialog.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SimulationVariablesDialog.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SimulationVariablesDialog.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SimulationVariablesDialog.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
}
