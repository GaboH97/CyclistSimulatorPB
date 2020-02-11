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
	
	/**
	 * 
	 * @param idCyclist
	 * @return
	 */
	public double getDistanceToNextBestCyclist(int idCyclist) {
		
		Cyclist cyclist = this.racers.stream().filter(racer -> racer.getId() == idCyclist).findFirst().get();
		
		int cyclistIdx = this.racers.indexOf(cyclist);
		//Esto sucede cuando es el primero de la lista
		if (cyclistIdx == 0) {
			return Double.MAX_VALUE;
		} else {
		
			Cyclist nextBestPositionedCyclist = this.racers.get(cyclistIdx - 1);
			double distancia = nextBestPositionedCyclist.getLocation().getX()
					- cyclist.getLocation().getX();
			
			//La distancia estará acotada a un mínimo de 0.2m, a partir de ahí, su distancia es despreciable
			return (distancia < 0.2 ? 0.2 : distancia);
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
	private Optional<PaveSection> getPaveSectionCyclistIsIn(Cyclist cyclist) {
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
	
	public void adjustFatigueForAllRacers() {
		this.racers.forEach(this::getAdjustedFatigueAccordingToPhenomena);
	}
	/**
	 * Método que ajusta la fatiga de un ciclista de acuerdo a la posición donde se encuentre.
	 * Puede darse el caso que no se encuentre en una sección de pavé, por lo que su factor de
	 * fatiga se preserva. De no suceder ello, se escala la fatiga de acuerdo a un factor de es-
	 * cala dado por la dificultad del segmento de pavé por donde el ciclista esté pasando
	 * 
	 * @param cyclist El ciclista en cuestión
	 * @return El factor de fatiga ajustado
	 */
	public double getAdjustedFatigueAccordingToPhenomena(Cyclist cyclist) {

		//METER LA FATIGA DE ACUERDO A LA VELOCIDAD
		
		Optional<PaveSection> paveSectionOp = getPaveSectionCyclistIsIn(cyclist);
		double adjustedFatigue = cyclist.getFatigue();

		if (paveSectionOp.isPresent()) {
			System.out.println("pave!");
			PaveSection paveSection = paveSectionOp.get();
			switch (paveSection.getDifficulty()) {
			case 1:
				adjustedFatigue += RaceConstants.FATIGUE_SCALE_FACTOR_1S_PAVE;
				break;
			case 2:
				adjustedFatigue += RaceConstants.FATIGUE_SCALE_FACTOR_2S_PAVE;
				break;
			case 3:
				adjustedFatigue += RaceConstants.FATIGUE_SCALE_FACTOR_3S_PAVE;
				break;
			case 4:
				adjustedFatigue += RaceConstants.FATIGUE_SCALE_FACTOR_4S_PAVE;
				break;
			case 5:
				adjustedFatigue += RaceConstants.FATIGUE_SCALE_FACTOR_5S_PAVE;
				break;
			} 
			//adjustedFatigue = adjustFatiqueFactorAccordingToWeightAndPaveSection(cyclist, paveSection);
		}
		return adjustedFatigue;
	}
	
	/**
	 * Método que ajusta la fatiga del ciclista de acuerdo a la distancia a la que se encuentre del
	 * ciclista que se encuentre adelante.
	 * 
	 * @param cyclist
	 */
	public void adjustCyclistFatigueAccordingToClosenesToNextBestCyclist(Cyclist cyclist) {
		cyclist.setFatigue(cyclist.getFatigue() - (1 / (5 * getDistanceToNextBestCyclist(cyclist.getId()))));
	}
	
	/**
	 * 
	 * @param cyclist
	 * @param paveSection
	 * @return
	 */
	private double adjustFatiqueFactorAccordingToWeightAndPaveSection(Cyclist cyclist, PaveSection paveSection) {
		
		double adjustedFatigue = cyclist.getFatigue();
		
		double fatigueFactorAdjustment = cyclist.getWeight() * paveSection.getDifficulty();
		
		return adjustedFatigue;
	}
	

	/**
	 * Método que imprime los ciclistas
	 */
	public void printAllRacers() {
		this.racers.forEach(System.out::println);
	}
	
	/**
	 * Método que ordena
	 */
	public void sortRacersAtTheEnd() {
		
		Comparator<Cyclist> compareByCyclistState = Comparator.comparing(Cyclist::getCyclistState);
		Comparator<Cyclist> compareByCyclistLocation = Comparator.comparing(Cyclist::getLocationOnXAxis).reversed();
		
		Collections.sort(racersAtTheEnd, compareByCyclistState.thenComparing(compareByCyclistLocation));
	}
	
	public static void main(String[] args) {
		Race race = new Race();
		race.setRacersAtTheEnd(race.getRacers());
		System.out.println("Wuthout order");
		race.getRacersAtTheEnd().forEach(System.out::println);
		race.sortRacersAtTheEnd();
		System.out.println("Ordered order");
		race.getRacersAtTheEnd().forEach(System.out::println);
		
	}
}
