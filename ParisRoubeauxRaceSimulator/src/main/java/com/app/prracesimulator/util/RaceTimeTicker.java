package com.app.prracesimulator.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@AllArgsConstructor
public class RaceTimeTicker {

    /**
     * Variable que controla el tiempo del sistema, la escala a utilizar es segundos
     */
    private long time;
    /**
     * Variable que controla la longitud de los intervalos de tiempo (por defecto es 1 segundo)
     */
    private long delta;
    /**
     * Variable que controla la velocidad con la que se ejecuta el programa, la cual emula los FPS
     */
    private long refreshRate;

    private static RaceTimeTicker instance;

    public static RaceTimeTicker getInstance() {
        if (instance == null) instance = new RaceTimeTicker();
        return instance;
    }

    private RaceTimeTicker() {
        this.time = 0L;
        this.delta = 1L;
        this.refreshRate = 60;
    }

    /**
     * Método que hace que el contador de segundos avance en el tiempo
     */
    public void advance(){
        this.time += delta;
    }

    public long deltaToMillis(){
        return TimeUnit.SECONDS.toMillis(refreshRate);
    }
}
