package com.app.prracesimulator.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class CyclistSequence {
    
    public static final AtomicInteger CYCLIST_ID = new AtomicInteger(1);
    
    public static int getNextInt(){
        return CYCLIST_ID.getAndIncrement();
    }
}
