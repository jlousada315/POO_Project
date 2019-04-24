package aco_sim;

import java.util.Random;
import java.lang.Math;

public class Prob {
	
	static Random rand; 
		
	public Prob() {
		// TODO Auto-generated constructor stub
		rand = new Random();
	}
	
	public static double expRand(double m) {
		double next = rand.nextDouble();
		return -m*Math.log(1.0-next);
	}		
	
	public int uniformDist(int max) {
		int next = rand.nextInt(max);
		return next;
	}
}
