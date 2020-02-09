package com.app.prracesimulator.models.entities;

import com.app.cyclistsimulatorpb.util.DataExtractor;
import com.app.prracesimulator.util.Constants;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;
import com.app.prracesimulator.util.RaceTimeTicker;
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
    private ArrayList<PaveSection> paveSegments;
    private ArrayList<CyclistLocation> locationsInPeloton;

    public Race() {
        racersOriginal = new ArrayList<>();
        paveSegments = new ArrayList<>();
        locationsInPeloton = new ArrayList<>();
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
     * fatiga.
     * Los valores de potencia están dados por la tabla unificada de valores de máxima potencia de los cuales se tiene
     * en cuenta solo aquellos que corresponden a ciclistas 'World Class' y 'Exceptional'
     *
     * @see <a href="https://www.cyclinganalytics.com/blog/2012/06/watts-kg-on-the-power-curve">Tabla de Potencias</a
     */
    public void setUpRacers() {	
    	Random rand = new Random();
    	for (int i = 1; i <= RaceConstants.NUMBER_OF_CYCLISTS; i++) {
        	this.racersOriginal.add(new Cyclist(i, rand));
		}
    	this.locateCyclistStart();
    }

    /**
     * Método que crea instancias de segmentos de Pavé a partir de un archivo
     * CSV que contiene los datos de dichos segmentos
     */
    public void setUpPaveSections() {
        List<List<Double>> paveDataMatrix = DataExtractor.readFromCSV(
                Constants.PAVE_SECTIONS_DATA_FILE_PATH
        );
        paveDataMatrix.stream().map(paveSectionData -> {
            int id = paveSectionData.get(0).intValue();
            int start = paveSectionData.get(1).intValue();
            int length = paveSectionData.get(2).intValue();
            int difficulty = paveSectionData.get(3).intValue();
            return new PaveSection(id, start, length, difficulty);
        }).forEach(paveSegments::add);
    }

    /**
     * metodo que crea la ubicacion de todos los ciclistas al inicio de la
     * carrera en la ubicacion metro cero y con 0 metros de distancia entre
     * ellos
     */
    private void locateCyclistStart() {
        for (Cyclist cyclist : racersOriginal) {
			this.locationsInPeloton.add(new CyclistLocation(cyclist, 0));
		}
    }

    /**
     * Método organiza los ciclistas de acuerdo a su posición en la carrera
     */
    public void updateCyclistRankingPositions(){
        this.racersOriginal.sort(Comparator.comparing(c -> c.getLocation().getX()));
    }

    /**
     * Método que evalua si el ciclista se encuentra en un tramo de pavé
     * @param cyclist
     * @return
     */
    public boolean isInPaveSection(Cyclist cyclist){
        Point2D.Double cyclistLoc = cyclist.getLocation();
        return paveSegments.stream().anyMatch(paveSection -> paveSection.getStart() <= cyclistLoc.getX() && cyclistLoc.getX() <= paveSection.getEnd());
    }

    /**
     * Método que identifica el segmento de pavé en el cual se encuentra el ciclista
     * @param cyclist
     * @return
     */
    public Optional<PaveSection> getPaveSectionCyclistIsIn(Cyclist cyclist){
        Point2D.Double cyclistLoc = cyclist.getLocation();
        return paveSegments.stream()
                 .filter(
                        paveSection -> paveSection.getStart() <= cyclistLoc.getX() && cyclistLoc.getX() <= paveSection.getEnd()
                ).findFirst();
    }

    /**
     * Método que evalua si la carrera ha terminado, esto sucede solo si todos los participantes de la carrera
     * han pasado la línea de meta
     *
     * @return
     */
    public boolean hasFinished(){
        return this.racersOriginal.stream().allMatch(racer -> racer.getLocation().getX() >= RaceConstants.RACE_LENGTH);
    }
}
