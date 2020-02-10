package com.app.prracesimulator.util;

import java.util.Random;

/**
 * 
 * @author Lenovo
 *
 */
public class Util {
	
	public static int getRandomBetween(Random rand, int lowerBound, int upperBound) {
		return rand.ints(lowerBound,upperBound).findFirst().getAsInt();
	}
	
	public static double getRandomBetween(Random rand, double lowerBound, double upperBound) {
		return rand.doubles(lowerBound,upperBound).findFirst().getAsDouble();
	}
	
}
