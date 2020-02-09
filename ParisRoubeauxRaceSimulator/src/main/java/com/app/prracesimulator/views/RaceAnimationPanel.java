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
import com.app.prracesimulator.models.entities.Race;

public class RaceAnimationPanel extends JPanel {

	public ArrayList<Cyclist> racers;
	public ArrayList<PaveSection> paveSegments;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RaceAnimationPanel(Controller controller) {
		super();
		setBackground(Color.white);		
		this.racers = controller.getRace().getRacers();
		this.paveSegments = controller.getRace().getPaveSegments();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		//hay que pintar el terreno de la carrera
		this.paintGroundRace(g2D);
		//aca se pintan los ciclistas
		paintRacers(g2D);		
	}

	/**
	 * metodo en el que se pintan los racers deacuerdo a su ubicacion y su estado
	 * @param g2D
	 */
	private void paintRacers(Graphics2D g2D) {
		for (Cyclist cyclist : racers) {
			// segun el estado del ciclista se debe pintar de un color u otro
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
			g2D.drawOval((int) cyclist.getLocation().getX(), // ubicacion en x del ovalo
					(int) cyclist.getLocation().getY(),//ubicacion en y del ovalo
						10,//ancho 
					10);// alto

		}
	}
	
	/**
	 * aca se debe pintar la carrera deacuerdo a los segmentos de pave
	 * @param g2D
	 */
	private void paintGroundRace(Graphics2D g2D) {
		
	}

	/**
	 * metodoi quye se llama desde el controler y actualiza la posicion de los racers
	 * @param racers
	 */
	public void setRacersPositions(ArrayList<Cyclist> racers) {
		this.racers = racers;
		this.repaint();//esto se tiene que dejar al final de todo el dibujo que se haga

	}
}