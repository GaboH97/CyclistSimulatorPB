package com.app.prracesimulator.models.entities;

import com.app.cyclistsimulatorpb.util.DataExtractor;
import com.app.prracesimulator.util.Constants;
import com.app.prracesimulator.util.CyclistSequence;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private RaceTimeTicker raceTimeTicker;

    private ArrayList<Cyclist> racers;
    private ArrayList<PaveSection> paveSegments;
    private ArrayList<CyclistLocationMeter> historicalLocationsForEachMeter;

    public Race() {
        racers = new ArrayList<>();
        paveSegments = new ArrayList<>();
        historicalLocationsForEachMeter = new ArrayList<>();
        setUpRace();
        this.race();
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

        List<List<Double>> maxPowOutputValuesMatrix = DataExtractor.readFromCSV(
                Constants.MAX_POW_OUTPUT_FILE_PATH
        );

        Random rand = new Random();

        for (int i = 0; i < RaceConstants.NUMBER_OF_CYCLISTS; i++) {

            List<Double> maxPowValues = maxPowOutputValuesMatrix.get(
                    rand.nextInt(maxPowOutputValuesMatrix.size())
            );
            double fiveSecsPower = maxPowValues.get(0);
            double oneMinPower = maxPowValues.get(1);
            double fiveMinPower = maxPowValues.get(2);
            double oneHourPower = maxPowValues.get(3);

            racers.add(new Cyclist(CyclistSequence.getNextInt(),
                    fiveSecsPower,
                    oneMinPower,
                    fiveMinPower,
                    oneHourPower,
                    rand.ints(Cyclist.MIN_WEIGHT, (Cyclist.MAX_WEIGHT + 1)).findFirst().getAsInt(),
                    rand.ints(Cyclist.MIN_FITNESS_FACTOR, (Cyclist.MAX_FITNESS_FACTOR + 1)).findFirst().getAsInt(),
                    rand.ints(Cyclist.MIN_FATIGUE_FACTOR, (Cyclist.MAX_FATIGUE_FACTOR + 1)).findFirst().getAsInt(),
                    new Point2D.Double(0,1),
                    1.0,
                    2.0
                    )
            );
        }
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
     * metodo que se encarga de realizar la carrera, para lo cual 1.
     * inicialmente debera ubicar a los ciclistas al inicio de la carrera (se
     * crean historios de los ciclistas en el metro 0) 2. se debe avanzar la
     * carrera metro por metro durante cada uno de los cuales se hace llamado al
     * metodo "raceNextMeter" el cual retorna los valores a agregar al historico
     * de ubicaciones de todos los ciclistas para cada uno de los metros, esto
     * hasta acabar la carrera
     *
     */
    public void race() {
        locateCyclistStart();// se ubican a los ciclistas en la linea de salida
        // se recorre cada uno de los metros que tiene la carrera,
        // se ira avanzando de metro en metro
        /**
         * 
         */
//        for (int i = 0; i < RaceConstants.RACE_LENGTH; i++) {
//            this.historicalLocationsForEachMeter.add(new CyclistLocationMeter(i, raceNextMeter(historicalLocationsForEachMeter.get(historicalLocationsForEachMeter.size()).getCyclistsLocation())));
//        }
    }

    /**
     * metodo que crea la ubicacion de todos los ciclistas al inicio de la
     * carrera en la ubicacion metro cero y con 0 metros de distancia entre
     * ellos
     */
    private void locateCyclistStart() {

        List<CyclistLocation> locations = racers.stream().map(
                racer -> new CyclistLocation(racer, racer.getId(), 0)
        ).collect(Collectors.toList());

    }

    /**
     * metodo que corre el siguiente metro de carrera para lo cual solicita la
     * ubicacion de todos los ciclistas en el metro previo al que se quiere
     * correr
     *
     * @param locations
     * @return
     */
    private ArrayList<CyclistLocation> raceNextMeter(ArrayList<CyclistLocation> locations) {
        ArrayList<CyclistLocation> newLocations = new ArrayList<CyclistLocation>();
//aca se guardaran las nuevas ubicaciones tras correr el metro
        /* Para cada una de las ubicaciones de los ciclistas se debe determinar cuanto se debe avanzar 
		 * teniendo en cuenta los valores de fatiga, fitness y los perfiles de poder de cada uno de ellos
		 * y se debe avanzar hasta que se complete la carrera
		 * 
		 * Se tiene que definir modelo matematico para que avancen los ciclistas teniendo en cuenta que 
		 * 1. dependiendo de lo que avance un ciclista con respecto al siguiente mejor este aumentara su
		 * fatiga o la disminuira, si logra mantenerse detras de este
		 * 2. si se tiene mucha fatiga este no podra avanzar demasiado lo que lo forzara a estar detras 
		 * del siguiente ciclista mejor posicionado 
		 * 3. si un ciclista tiene al siguiente mejor ubicado a cierta distancia el nivel de recuperacion 
		 * disminuira en una proporcion al cuadrado... es decir si se encuentra a una distancia de 1 metro
		 * se recuperara normalmente, si se encuentra a 2 metros se recuperara 4 veces mas lento y si se
		 * encuentra a mas de 3 metros se recuperara 9 veces mas lento Para lo cual es necesario determinar
		 * un indice de recuperacion base teniendo en cuenta el nivel de fatiga que tiene el ciclista y a 
		 * partir de este calcular la cantidad de recuperacion que tendra.
         */
        for (int i = 0; i < locations.size(); i++) {
        }
        return null;
    }


    /**
     * Método organiza los ciclistas de acuerdo a su posición en la carrera
     */
    public void updateCyclistRankingPositions(){
        this.racers.sort(Comparator.comparing(c -> c.getLocation().getX()));
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
        return this.racers.stream().allMatch(racer -> racer.getLocation().getX() >= RaceConstants.RACE_LENGTH);
    }
}
