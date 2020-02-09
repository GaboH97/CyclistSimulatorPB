package com.app.prracesimulator.models.entities;

import com.app.cyclistsimulatorpb.util.DataExtractor;
import com.app.prracesimulator.util.Constants;
import java.awt.geom.Point2D;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * clase que determina la carrera en esta se almacenan los ciclistas que
 * compiten, los segmentos de pave y el historico de la ubicacion de los
 * ciclistas esto con el fin de graficarlos posteriormente
 *
 * @author jacr
 *
 */
@Getter
@Setter
public class Race {

	private ArrayList<Cyclist> racersOriginal;
	private ArrayList<Cyclist> racers;
	private ArrayList<Cyclist> racersAtTheEnd;
	private ArrayList<PaveSection> paveSegments;

	public Race() {
		racersOriginal = new ArrayList<>();
		racers = new ArrayList<>();
		racersAtTheEnd = new ArrayList<>();
		paveSegments = new ArrayList<>();
		this.setUpRace();
	}

	/**
	 * Método que da la configuración inicial de la carrera
	 */
	public void setUpRace() {
		setUpRacers();
		setUpPaveSections();
	}

	/**
	 * Método que crea los ciclistas con valores pseudoaleatorios normalmente
	 * distribuidos en lo que respecta a atributos de peso, factor de fitness y
	 * fatiga. Los valores de potencia están dados por la tabla unificada de valores
	 * de máxima potencia de los cuales se tiene en cuenta solo aquellos que
	 * corresponden a ciclistas 'World Class' y 'Exceptional'
	 *
	 * @see <a href=
	 *      "https://www.cyclinganalytics.com/blog/2012/06/watts-kg-on-the-power-curve">Tabla
	 *      de Potencias</a
	 */
	public void setUpRacers() {
		Random rand = new Random();
		for (int i = 1; i <= RaceConstants.NUMBER_OF_CYCLISTS; i++) {
			this.racersOriginal.add(new Cyclist(i, rand));
			this.racers.add(new Cyclist(i, rand));
		}
	}

	/**
	 * Método que crea instancias de segmentos de Pavé a partir de un archivo CSV
	 * que contiene los datos de dichos segmentos
	 */
	public void setUpPaveSections() {
		List<List<Double>> paveDataMatrix = DataExtractor.readFromCSV(Constants.PAVE_SECTIONS_DATA_FILE_PATH);
		paveDataMatrix.stream().map(paveSectionData -> {
			int id = paveSectionData.get(0).intValue();
			int start = paveSectionData.get(1).intValue();
			int length = paveSectionData.get(2).intValue();
			int difficulty = paveSectionData.get(3).intValue();
			return new PaveSection(id, start, length, difficulty);
		}).forEach(paveSegments::add);
	}

	/**
	 * Método organiza los ciclistas de acuerdo a su posición en la carrera
	 */
	public void updateCyclistRankingPositions() {
		this.racers.sort(Comparator.comparing(c -> -c.getLocation().getX()));
	}

	public double getNextBestInMeters(int idCyclist) {
		Cyclist ciclistaEnCuestion = this.racers.stream().filter(racer -> racer.getId() == idCyclist).findFirst().get();
		int ubicacionEnElArrayDelCilstaEnCuestion = this.racers.indexOf(ciclistaEnCuestion);
		if (ubicacionEnElArrayDelCilstaEnCuestion == 0) {
			return Double.MAX_VALUE;
		} else {
			Cyclist mejorQueElCiclistaEnCuestion = this.racers.get(ubicacionEnElArrayDelCilstaEnCuestion - 1);
			double distancia = mejorQueElCiclistaEnCuestion.getLocation().getX() - ciclistaEnCuestion.getLocation().getX();
			return (distancia<0.2?0.2:distancia);
		}
	}

	/**
	 * Método que evalua si el ciclista se encuentra en un tramo de pavé
	 * 
	 * @param cyclist
	 * @return
	 */
	public boolean isInPaveSection(Cyclist cyclist) {
		Point2D.Double cyclistLoc = cyclist.getLocation();
		return paveSegments.stream().anyMatch(paveSection -> paveSection.getStart() <= cyclistLoc.getX()
				&& cyclistLoc.getX() <= paveSection.getEnd());
	}

	/**
	 * Método que identifica el segmento de pavé en el cual se encuentra el ciclista
	 * 
	 * @param cyclist
	 * @return
	 */
	public Optional<PaveSection> getPaveSectionCyclistIsIn(Cyclist cyclist) {
		Point2D.Double cyclistLoc = cyclist.getLocation();
		return paveSegments.stream().filter(
				paveSection -> paveSection.getStart() <= cyclistLoc.getX() && cyclistLoc.getX() <= paveSection.getEnd())
				.findFirst();
	}

	/**
	 * Método que evalua si la carrera ha terminado, esto sucede solo si todos los
	 * participantes de la carrera han pasado la línea de meta
	 *
	 * @return
	 */
	public boolean hasFinished() {
		return this.racersOriginal.size() == this.racersAtTheEnd.size();
	}
}
