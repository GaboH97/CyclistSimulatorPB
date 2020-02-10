package com.app.prracesimulator.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;

public class CompetitorsPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public CompetitorsPanel(Controller controller) {
		setLayout(new GridLayout(4, 5));
		add(new JLabel("Competidores"));
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		for (Cyclist cyclist : racers) {
			add(new JLabel("Ciclista" + cyclist.getId()));
		}
	}
}
