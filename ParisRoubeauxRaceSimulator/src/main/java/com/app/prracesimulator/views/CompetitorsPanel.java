package com.app.prracesimulator.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.sound.midi.Receiver;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;

public class CompetitorsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public CompetitorsPanel(Controller controller) {
		setLayout(new GridLayout(4, 5));
		add(new JLabel("Competidores"));
		setBackground(Color.WHITE);
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		removeAll();
		System.err.println(racers.size());
		for (Cyclist cyclist : racers) {
			JLabel lbCyclist = new JLabel(".   Ciclista" + cyclist.getId());
			lbCyclist.setForeground(cyclist.getCyclistState().equals(CyclistState.RACING) ? Color.BLUE
					: cyclist.getCyclistState().equals(CyclistState.FINISHER) ? Color.GREEN : Color.RED);
			add(lbCyclist);
			repaint();
		}
		revalidate();
	}
}
