package com.app.prracesimulator.views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;

public class CompetitorsPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public CompetitorsPanel(Controller controller) {
		add(new JLabel("Competidores"));
	}
}
