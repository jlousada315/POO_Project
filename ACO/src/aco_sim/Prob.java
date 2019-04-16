package aco_sim;

import java.util.Random;
import java.lang.Math;


public class Prob {
	
	static Random rand = new Random();
		
	public Prob() {
		// TODO Auto-generated constructor stub
	}
	
	public static double expRand(double m) {
		double next = rand.nextDouble();
		return -m*Math.log(1.0-next);
	}	
	
}
