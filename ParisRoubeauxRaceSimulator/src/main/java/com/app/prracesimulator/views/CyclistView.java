package com.app.prracesimulator.views;

import javax.swing.JProgressBar;

import com.app.prracesimulator.models.entities.Cyclist;

public class CyclistView {

	private Cyclist cyclist;
	private JProgressBar jProgressBar;

	public CyclistView(Cyclist cyclist, JProgressBar jProgressBar) {
		super();
		this.cyclist = cyclist;
		this.jProgressBar = jProgressBar;
	}

	public Cyclist getCyclist() {
		return cyclist;
	}

	public void setCyclist(Cyclist cyclist) {
		this.cyclist = cyclist;
	}

	public JProgressBar getjProgressBar() {
		return jProgressBar;
	}

	public void setjProgressBar(JProgressBar jProgressBar) {
		this.jProgressBar = jProgressBar;
	};

}
