package com.app.prracesimulator.views;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.PaveSection;
import com.app.prracesimulator.models.entities.RaceConstants;

/**
 * Clase que contiene el croquis de la carrera Paris-Roubaix
 * 
 * @author Yuliana
 *
 */
public class RaceSketch extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Cyclist> racers;
	public ArrayList<PaveSection> paveSegments;
	
	private int pixelesRuta = 0;
	
	/**
	 * Constructor de la clase, que inicializa todos los valores necesarios para el
	 * pintado de la carrera.
	 */
	public RaceSketch(Controller controller, int sizeX, int sizeY) {
		this.pixelesRuta = sizeX;
		this.racers = controller.getRace().getRacers();
		this.paveSegments = controller.getRace().getPaveSegments();
	}

	/**
	 * Pinta la carrera
	 */
	@Override
	public void paint(Graphics g) {
		// Se crea un BufferedImage y coge el Graphics2D del mismo, que es donde
		// pintaremos. Ya que s�lo se puede dibujar sobre un objeto Graphics2D
		BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gbuffer = buffer.getGraphics();

		// Pinta el fondo con color azul
		pintarFondo(gbuffer, Color.decode("#81DAF5"));

		// Pinta la carretera con color verde
		pintarCarretera(gbuffer, Color.decode("#0B610B"));

		// Pinta separaciones kilom�tricas de negro
		pintarSeparacionesKilometricas(gbuffer, Color.BLACK, 10);

		// Actualiza el buffer en el canvas
		g.drawImage(buffer, 0, 0, this);
		

		drawCyclists(g);	
	}

	private void drawCyclists(Graphics g) {
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
					100);// alto

		}
	}
	
	/**
	 * Dibuja el cielo sobre el buffer (Graphics) que sirve como imagen de fondo.
	 * 
	 * @param g     Graphics sobre el que se pintan todas las im�genes.
	 * @param color Color del fondo
	 */
	private void pintarFondo(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Dibuja la carretera 
	 * @param g     Graphics sobre el que se pintan todas las im�genes.
	 * @param color Color de la carretera
	 */
	private void pintarCarretera(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(0, getHeight()/2 + 10 , getWidth(), getHeight()/2 );
	}


	/**
	 * Dibuja las marcas que denotan los Km de la ruta
	 * 
	 * @param g         Graphics 
	 * @param color     Color de la linea que indican cada kilometro
	 * @param distancia Distancia de separacion (en Km) entre las lineas
	 */
	private void pintarSeparacionesKilometricas(Graphics g, Color color, int distancia) {
		g.setColor(color);
		int numerodelineas = RaceConstants.RACE_LENGTH / distancia;
		double distanciapixelsentrelineas = distancia * pixelesRuta / RaceConstants.RACE_LENGTH;
		
		for (int i = 0; i < numerodelineas + 1; i++) {
			g.drawLine((int) (i * distanciapixelsentrelineas), (int) (700), //X
					(int) (i * distanciapixelsentrelineas), (int) (400 - 15));//Y
			g.drawString(Integer.toString(i * distancia), (int) (i * distanciapixelsentrelineas),
					(int) (400 - 18)); //Y
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
