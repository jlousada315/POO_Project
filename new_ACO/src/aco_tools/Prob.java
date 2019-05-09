package aco_tools;

import java.util.Random;
import java.lang.Math;

/**
 *  Obtain random values from uniform or 
 *  exponential distributions with boundaries.
 */
public class Prob {
	/**
	 * random cumulative exponential distribution.
	 * 
	 * @param  m         mean value of exponential distribution.
	 * @return double    random value from exponential distribution.
	 */
	public static double expRand(double m) {
		Random rand = new Random();
		double next = rand.nextDouble();
		return -m*Math.log(1.0-next);
	}		
	
	/**
	 * random uniform distribution.
	 * 
	 * @param  max       maximum bound of random distribution.         
	 * @return double    random value from uniform distribution.
	 */
	public int uniformDist(int max) {
		Random rand = new Random();
		int next = rand.nextInt(max);
		return next;
	}
}