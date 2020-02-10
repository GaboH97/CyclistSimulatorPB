package com.app.prracesimulator.views;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;

public class TopCyclistPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TopCyclistPanel(Controller controller) {
		setBackground(Color.WHITE);
		this.add(new JLabel("Top 10"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		// removeAll();
		for (Cyclist cyclist : racers) {
			add(new JLabel(".   Ciclista" + cyclist.getId()));
		}
		add(new JLabel("_________________________"));
		repaint();
		revalidate();
	}
}
