package main;

import xml_utils.*;
import simulation.Simulator;


public class TsfAcoSimulator {
	public static void main(String[] args)  {
		Simulator Sim = new Simulator((new XMLUtils(args[0])).getV()) ;
		Sim.run();
	}
}