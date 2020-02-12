package com.app.prracesimulator.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.sound.midi.Receiver;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;

public class CompetitorsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public ArrayList<Cyclist> racers;
	private JPanel jp;
	
	public CompetitorsPanel(Controller controller) {
		 this.setLayout(new BorderLayout());
	        setBorder(BorderFactory.createTitledBorder(" "));
	        setBackground(Color.decode("#f0f0f0"));
	        JLabel lbTitlePanel = new JLabel("Progreso competidores");
	        lbTitlePanel.setFont(new java.awt.Font("Tahoma", 1, 15));
	        lbTitlePanel.setForeground(Color.decode("#be0027"));
	        this.add(lbTitlePanel, BorderLayout.PAGE_START);

	       jp = new JPanel();
		
		this.racers = controller.getRace().getRacers();
		setBackground(Color.WHITE);
		
		jp.setLayout(new GridLayout(20,3, 10, 20));
		
		pintarListaCiclistas();
	}

	private void pintarListaCiclistas() {
		for (Cyclist cyclist : this.racers) {
			JLabel lbCyclist = new JLabel(".   Ciclista" + cyclist.getId());
			lbCyclist.setForeground(cyclist.getCyclistState().equals(CyclistState.RACING) ? Color.BLUE
					: cyclist.getCyclistState().equals(CyclistState.FINISHER) ? Color.GREEN : Color.RED);
			jp.add(lbCyclist);
			JProgressBar	progressBar = new JProgressBar(0);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
			jp.add(progressBar);
			
		}
		add(jp, BorderLayout.CENTER);
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		
	}
}
