package com.app.prracesimulator.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaceTimeTicker {

    private long time;
    private static long deltha = 1L;

    private static RaceTimeTicker instance;

    public static RaceTimeTicker getInstance() {
        if(instance == null) instance = new RaceTimeTicker();
        return instance;
    }

    private RaceTimeTicker() {
        this.time = 0L;
    }

    public static long getDeltha() {
        return deltha;
    }

    /**
     * Método que hace que el contador de segundos avance en el tiempo
     */
    public void advance(){
        this.time += deltha;
    }
}
