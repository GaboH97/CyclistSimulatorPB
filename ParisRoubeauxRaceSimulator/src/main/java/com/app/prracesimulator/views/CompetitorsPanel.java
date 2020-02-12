package com.app.prracesimulator.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.sound.midi.Receiver;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;
import com.app.prracesimulator.models.entities.RaceConstants;

public class CompetitorsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public ArrayList<Cyclist> racers;
	private ArrayList<CyclistView> racersView;
	private JPanel jp;

	public CompetitorsPanel(Controller controller) {
		this.racers = controller.getRace().getRacers();
		this.racersView =  new ArrayList<>();
		initComponents(controller);
		pintarListaCiclistas();
	}

	private void initComponents(Controller controller) {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(" "));
		this.setBackground(Color.WHITE);
		
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.WHITE);
		pnTitle.setLayout(new GridLayout(2,1,0,0));
		JLabel lbTitlePanel = new JLabel("  Progreso competidores");
		lbTitlePanel.setFont(new java.awt.Font("Tahoma", 1, 15));
		lbTitlePanel.setForeground(Color.decode("#be0027"));
		pnTitle.add(lbTitlePanel);
		
		pnTitle.add( new JLabel(" "));
		this.add(pnTitle, BorderLayout.PAGE_START);

		jp = new JPanel();
		if(racers.size()  >= 5) {
			jp.setLayout(new GridLayout(racers.size() / 2 + 1, 3, 10, 20));
		}else {
			jp.setLayout(new GridLayout(4, 1, 10, 20));
		}
		jp.setBackground(Color.WHITE);
	}

	/**
	 * Metodo que pinta los ciclistas y asigna un progressbar de rendimiento para cada uno de ellos
	 */
	private void pintarListaCiclistas() {
		for (Cyclist cyclist : this.racers) {
			JLabel lbCyclist = new JLabel("   Ciclista" + cyclist.getId() + ": ", SwingConstants.CENTER);
			lbCyclist.setForeground(cyclist.getCyclistState().equals(CyclistState.RACING) ? Color.BLUE
					: cyclist.getCyclistState().equals(CyclistState.FINISHER) ? Color.GREEN : Color.RED);
			jp.add(lbCyclist);
			JProgressBar progressBar = new JProgressBar(0);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
			jp.add(progressBar);
			this.racersView.add(new CyclistView(cyclist, progressBar));
		}
		add(jp, BorderLayout.CENTER);
	}

	/**
	 * Actualiza los progresos de los ciclistas
	 * @param racers
	 */
	public void setRacers(ArrayList<Cyclist> racers) {
		for (Cyclist cyclist : racers) {
			this.racersView.get(cyclist.getId() - 1 ).getjProgressBar().setValue((int)cyclist.getLocation().getX() * 100 / RaceConstants.RACE_LENGTH);
			DecimalFormat formato1 = new DecimalFormat("0.00");
			this.racersView.get(cyclist.getId()- 1 ).getjProgressBar().setString((formato1.format((double)cyclist.getLocation().getX()*100/RaceConstants.RACE_LENGTH) + " %")); 
		}
		this.repaint();
	}
}
