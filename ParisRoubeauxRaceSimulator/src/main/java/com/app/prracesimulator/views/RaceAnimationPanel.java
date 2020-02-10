package com.app.prracesimulator.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.PaveSection;
import com.app.prracesimulator.models.entities.RaceConstants;

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
		int sizeX = 16000 ;
		int sizeY = 349 ;		
		raceSketch =  new RaceSketch(controller, sizeX, sizeY);
		raceSketch.setPreferredSize(new Dimension(sizeX,sizeY));
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