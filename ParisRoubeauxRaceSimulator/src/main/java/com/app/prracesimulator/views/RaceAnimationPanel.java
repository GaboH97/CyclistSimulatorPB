package com.app.prracesimulator.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;

/**
 * Panel que contiene el canvas de la carrera
 * @author Yuliana
 *
 */
public class RaceAnimationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RaceSketch raceSketch;
	
	private static final int DistanciaCarreraPixeles = 17000;

	public RaceAnimationPanel(Controller controller) {
		super();
		setBackground(Color.GRAY);		
		
		this.initComponents(controller);
	}

	/**
	 * Metodo que inicia comonentes del dibujo del croquis de la carrera
	 * @param controller
	 */
	private void initComponents(Controller controller) {
		int sizeY = 349 ;		
		raceSketch =  new RaceSketch(controller, DistanciaCarreraPixeles, sizeY);
		raceSketch.setPreferredSize(new Dimension(DistanciaCarreraPixeles,sizeY));
		this.add(raceSketch);
	}



	/**
	 * metodoi quye se llama desde el controler y actualiza la posicion de los racers
	 * @param racers
	 */
	public void setRacersPositions(ArrayList<Cyclist> racers) {
		this.raceSketch.setRacersPositions(racers);
	}
}