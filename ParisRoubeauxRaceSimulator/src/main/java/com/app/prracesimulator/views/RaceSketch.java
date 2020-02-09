package com.app.prracesimulator.views;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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

	private int pixelesRuta = 2000;
	
	/**
	 * Constructor de la clase, que inicializa todos los valores necesarios para el
	 * pintado de la carrera.
	 */
	public RaceSketch() {
		
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
			g.drawString(Integer.toString(i * distancia), (int) (i * distanciapixelsentrelineas) - 5,
					(int) (400 - 18)); //Y
		}
	}




}
