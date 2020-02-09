package com.app.prracesimulator.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.PaveSection;

public class RaceAnimationPanel extends JPanel {

	private Controller controller;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RaceAnimationPanel(Controller controller) {
		super();
		setBackground(Color.BLACK);
//		this.set
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		for (Cyclist cyclist : controller.getRace().getRacers()) {
			//segun el estado del ciclista se debe pintar de un color u otro
			switch (cyclist.getCyclistState()) {
			case RACING:
				g2D.setColor(Color.ORANGE);
				break;
			case FINISHER:
				g2D.setColor(Color.GREEN);
				break;
			case DEQUALIFIED:
				g2D.setColor(Color.RED);
				break;

			default:
				break;
			}
			g2D.drawOval((int) cyclist.getLocation().getX(), //ubicacion en x del ovalo
					0,//ubicacion en y del ovalo
					10,//ancho 
					10);//alto
			this.repaint();//esto se tiene que dejar al final de todo el dibujo que se haga
		}
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		this.controller.getRace().setRacers(racers);
	}
}
