package com.app.prracesimulator.views;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.PaveSection;
import com.app.prracesimulator.models.entities.RaceConstants;
import com.app.prracesimulator.util.Constants;
import com.sun.javafx.geom.Line2D;

/**
 * Clase que contiene el croquis de la carrera Paris-Roubaix
 * 
 * @author Yuliana
 *
 */
public class RaceSketch extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Cyclist> racers;
	public ArrayList<PaveSection> paveSegments;

	private int pixelesRutaX = 0;
	private int pixelesRutaY = 0;

	// imagenes de los ciclistas
	private BufferedImage imagenciclista;
	private BufferedImage imagenredimensionada;

	private static final int CYCLIST_SIZE = 5;
	private static final int INTERVALOS_DISTANCIA_METROS = 1000;

	/**
	 * Constructor de la clase, que inicializa todos los valores necesarios para el
	 * pintado de la carrera.
	 */
	public RaceSketch(Controller controller, int sizeX, int sizeY) {
		this.pixelesRutaX = sizeX;
		this.pixelesRutaY = sizeY;
		
		this.racers = controller.getRace().getRacers();
		this.paveSegments = controller.getRace().getPaveSegments();

		obtenerImagenCiclista();
	}

	/**
	 * Obtiene la imagen del ciclista que se pintara y además la redimensiona
	 */
	private void obtenerImagenCiclista() {
		imagenciclista = null;
		try {
			imagenciclista = ImageIO.read(new File(Constants.CYCLIST_PATH));
			Image imagenredimensionadaaux = imagenciclista.getScaledInstance(imagenciclista.getWidth() / CYCLIST_SIZE,
					imagenciclista.getHeight() / CYCLIST_SIZE, BufferedImage.SCALE_SMOOTH);
			imagenredimensionada = new BufferedImage(imagenredimensionadaaux.getWidth(null),
					imagenredimensionadaaux.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			imagenredimensionada.getGraphics().drawImage(imagenredimensionadaaux, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
			imagenredimensionada = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}
	}

	/**
	 * Metodo principal que pinta todos los elementos que componen la simulación
	 */
	@Override
	public void paint(Graphics g) {
		BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gbuffer = buffer.getGraphics();

		pintarFondo(gbuffer, Color.decode("#81DAF5"));

		pintarCarretera(gbuffer, Color.decode("#0B610B"));

		pintarSegmentosPave(gbuffer, Color.WHITE);

		pintarSeparacionesKilometricas(gbuffer, Color.WHITE, INTERVALOS_DISTANCIA_METROS);

		g.drawImage(buffer, 0, 0, this);

		drawCyclists(g);
	}

	/**
	 * Dibuja el cielo sobre el buffer (Graphics) que sirve como imagen de fondo.
	 * 
	 * @param g     
	 * @param color
	 */
	private void pintarFondo(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Dibuja la carretera 
	 * 
	 * @param g    
	 * @param color 
	 */
	private void pintarCarretera(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(0, getHeight() / 2 + 10, getWidth(), getHeight() / 2);
	}

	/**
	 * Dibuja las marcas que denotan los Km de la ruta
	 * 
	 * @param g         
	 * @param color    
	 * @param distancia 
	 */
	private void pintarSeparacionesKilometricas(Graphics g, Color color, int distancia) {
		g.setColor(color);
		int numerodelineas = RaceConstants.RACE_LENGTH / distancia;
		 long distanciapixelsentrelineas = (long)  distancia * pixelesRutaX / RaceConstants.RACE_LENGTH;
		for (int i = 0; i < numerodelineas + 1 ; i++) { 
			g.drawLine((int) (i * distanciapixelsentrelineas), (int) (pixelesRutaY), (int) (i * distanciapixelsentrelineas), (int) (pixelesRutaY - 15));// x,y x,y
			g.drawString(Integer.toString(i * distancia / 1000) + "km", (int) (i * distanciapixelsentrelineas), (int) (pixelesRutaY - 18)); // Y
		}
	}

	/**
	 * Dibuja las marcas que denotan la posicion de las curvas sobre el croquis de
	 * la carrera
	 * 
	 * @param g     
	 * @param color 
	 */
	private void pintarSegmentosPave(Graphics g, Color color) {
		for (int i = 0; i < paveSegments.size(); i++) {
			int posicionInicioPave = paveSegments.get(i).getStart();
			long posicionlinea = ((long) posicionInicioPave * pixelesRutaX) / RaceConstants.RACE_LENGTH  ;
			pintarSegmentosPorSuDificultad(g, paveSegments.get(i), (int) posicionlinea);
			g.setColor(color);
			g.drawLine((int) posicionlinea, 0, (int) posicionlinea, pixelesRutaY);
			g.drawString(" " + Double.toString(paveSegments.get(i).getLength()), (int) posicionlinea, 10);
			g.drawString(" (m)", (int) posicionlinea, 22);
			g.drawString(" Dif. " + paveSegments.get(i).getDifficulty(), (int) posicionlinea, 35);
		}
	}

	/***
	 * Pinta los segmentos de pave y los colorea de acuerdo a su dificultad
	 * @param g
	 * @param paveSection
	 * @param posicionlinea
	 */
	private void pintarSegmentosPorSuDificultad(Graphics g, PaveSection paveSection, int posicionlinea) {
		switch (paveSection.getDifficulty()) {
		case 1:
			g.setColor(Color.decode("#F5DA81")); 
			break;
		case 2:
			g.setColor(Color.decode("#F7D358"));
			break;
		case 3:
			g.setColor(Color.decode("#B18904"));
			break;
		case 4:
			g.setColor(Color.decode("#FE9A2E"));
			break;
		case 5:
			g.setColor(Color.decode("#FF4000"));
			break;
		}

		g.fillRect(posicionlinea, getHeight() / 2 + 10,
				((paveSection.getLength()) * pixelesRutaX / RaceConstants.RACE_LENGTH), getHeight() / 2);
	}

	/**
	 * metodo en el que se pintan los racers deacuerdo a su ubicacion y su estado
	 * 
	 * @param g2D
	 */
	private void drawCyclists(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		// aca se pintan los ciclistas
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
			g.drawString(Integer.toString(cyclist.getId()), (int) cyclist.getLocation().getX() * pixelesRutaX / RaceConstants.RACE_LENGTH,
					(int) getHeight() / 2 - 25);
			g.drawImage(imagenredimensionada, (int) cyclist.getLocation().getX() * pixelesRutaX / RaceConstants.RACE_LENGTH, getHeight() / 2 - 25, this);
		}
	}

	/**
	 * metodoi quye se llama desde el controler y actualiza la posicion de los
	 * racers
	 * 
	 * @param racers
	 */
	public void setRacersPositions(ArrayList<Cyclist> racers) {
		this.racers = racers;
//		for (Cyclist cyclist : racers) {
//			System.out.println("posicion ciclista " + cyclist.getId() + ":: " + cyclist.getLocation().getX());
//		}
		this.repaint();
	}

	
	@Override
	public void update(Graphics g) {
		super.update(g);
		paint(g);
	}
}
