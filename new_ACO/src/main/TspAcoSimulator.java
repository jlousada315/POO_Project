package main;

import xml_utils.*;
import simulation.Simulator;


public class TspAcoSimulator {
	public static void main(String[] args)  {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments. One must be given - xml file.");
			System.exit(-1);
		}
		Simulator Sim = new Simulator((new XMLUtils(args[0])).getV()) ;
		Sim.run();
	}
}