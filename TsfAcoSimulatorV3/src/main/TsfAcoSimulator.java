package main;

import xml_utils.*;


public class TsfAcoSimulator {
	public static void main(String[] args)  {

		XMLUtils xml = new XMLUtils(args[0]);
		Var var = xml.getV();

		double[][] weights = new double[var.getWeight().length][var.getWeight().length];
		weights = var.getWeight();
		for(int i=0; i<weights.length; i++) {
			for(int j=i+1; j<weights.length; j++) 
					System.out.print(weights[i][j] + " ");
			System.out.println();
		}
	}
}
