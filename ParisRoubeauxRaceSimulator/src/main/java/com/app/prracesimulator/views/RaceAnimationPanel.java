package com.app.prracesimulator.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
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
	
	private int DistanciaCarreraPixeles = 10000;

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
		setLayout(new BorderLayout());
		validarTamanioPanel();
		int sizeY = 357 ;		
		raceSketch =  new RaceSketch(controller, DistanciaCarreraPixeles, sizeY);
		raceSketch.setPreferredSize(new Dimension(DistanciaCarreraPixeles,sizeY));
		this.add(raceSketch, BorderLayout.CENTER);
	}



	private void validarTamanioPanel() {
		if(RaceConstants.RACE_LENGTH >= 200000) {
			this.DistanciaCarreraPixeles = 17000;
		}else if(RaceConstants.RACE_LENGTH < 200000 && RaceConstants.RACE_LENGTH >= 50000  ) {
			this.DistanciaCarreraPixeles = 15000;
		}else if(RaceConstants.RACE_LENGTH < 50000 && RaceConstants.RACE_LENGTH > 10000  ) {
			this.DistanciaCarreraPixeles = 10000;
		}else if(RaceConstants.RACE_LENGTH <= 10000 && RaceConstants.RACE_LENGTH > 1000 ){
			this.DistanciaCarreraPixeles = 3000;
		}
	}

	/**
	 * metodoi quye se llama desde el controler y actualiza la posicion de los racers
	 * @param racers
	 */
	public void setRacersPositions(ArrayList<Cyclist> racers) {
		this.raceSketch.setRacersPositions(racers);
	}
}