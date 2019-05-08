package aco_tools;

import java.util.Random;
import java.lang.Math;

public class Prob {
	// random cumulative exponential distribution
	public static double expRand(double m) {
		Random rand = new Random();
		double next = rand.nextDouble();
		return -m*Math.log(1.0-next);
	}		
	
	// random uniform distribution
	public static int uniformDist(int max) {
		Random rand = new Random();
		int next = rand.nextInt(max);
		return next;
	}
}