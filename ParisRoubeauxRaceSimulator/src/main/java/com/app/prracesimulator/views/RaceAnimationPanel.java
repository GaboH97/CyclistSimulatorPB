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
		setBackground(Color.red);		
		
		this.initComponents(controller);
	}

	private void initComponents(Controller controller) {
		int sizeX = 1100 ;
		int sizeY = 400 ;		
		raceSketch =  new RaceSketch(controller, sizeX, sizeY);
		//this.setPreferredSize(new Dimension(sizeX, sizeY));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		raceSketch.setPreferredSize(new Dimension(sizeX,sizeY));
		this.add(raceSketch);
		
		//this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//this.setPreferredSize(new Dimension(sizeX,sizeY));
	}



	/**
	 * metodoi quye se llama desde el controler y actualiza la posicion de los racers
	 * @param racers
	 */
	public void setRacersPositions(ArrayList<Cyclist> racers) {
		this.raceSketch.setRacersPositions(racers);
	}
}