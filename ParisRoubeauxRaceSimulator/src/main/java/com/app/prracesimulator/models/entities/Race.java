package com.app.cyclistsimulatorpb.models.entities;

import java.util.ArrayList;

/**
 * clase que determina la carrera en esta se almacenan los ciclistas que
 * compiten, los segmentos de pave y el historico de la ubicacion de los
 * ciclistas esto con el fin de graficarlos posteriormente
 *
 * @author jacr
 *
 */
public class Race {

    private ArrayList<Cyclist> cyclists;
    private ArrayList<Pave> paveSegments;
    private ArrayList<CyclistLocationMeter> historicalLocationsForEachMeter;
    
    

    public Race() {
        cyclists = new ArrayList<Cyclist>();
        paveSegments = new ArrayList<Pave>();
        this.race();
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
        for (int i = 0; i < ConstantsUtil.RACE_LENGTH; i++) {
            this.historicalLocationsForEachMeter.add(new CyclistLocationMeter(i, raceNextMeter(historicalLocationsForEachMeter.get(historicalLocationsForEachMeter.size()).getCyclistsLocation())));
        }
    }

    /**
     * metodo que crea la ubicacion de todos los ciclistas al inicio de la
     * carrera en la ubicacion metro cero y con virtualmente 0 metros de
     * distancia entre ellos
     */
    private void locateCyclistStart() {
        ArrayList<CyclistLocation> locations = new ArrayList<CyclistLocation>();
        for (Cyclist cyclist : cyclists) {
            locations.add(new CyclistLocation(cyclist, cyclist.getId(), 0));
        }
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
        ArrayList<CyclistLocation> newLocations = new ArrayList<CyclistLocation>();//aca se guardaran las nuevas ubicaciones tras correr el metro
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

    // ---Metodos de inicializacion de objetos, (ciclistas y segmentos de pave)---
    /**
     * se crean los ciclistas deacuerdo al numero de ciclistas en la carrera
     */
    public void createCyclists() {
        for (int i = 0; i < ConstantsUtil.NUMBER_CYCLISTS; i++) {
            cyclists.add(new Cyclist());
        }
    }

    /**
     * se crean todos los segmentos de pave deacuerdo a la tabla
     *
     */
    public void createPaveSegments() {
        paveSegments.add(new Pave(29, 96500, 2200, 3));
        paveSegments.add(new Pave(28, 103500, 1800, 3));
        paveSegments.add(new Pave(27, 110000, 3700, 4));
        paveSegments.add(new Pave(26, 112500, 3000, 3));
        paveSegments.add(new Pave(25, 117000, 800, 2));
        paveSegments.add(new Pave(24, 124500, 2300, 3));
        paveSegments.add(new Pave(23, 134500, 1600, 3));
        paveSegments.add(new Pave(22, 137500, 2500, 3));
        paveSegments.add(new Pave(21, 140500, 1600, 3));
        paveSegments.add(new Pave(20, 153500, 2500, 3));
        // se deben agregar los demas segmentos
    }
}
