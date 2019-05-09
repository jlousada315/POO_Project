package main;

import xml_utils.*;

import simulation.Simulator;

/**
 * Runs the simulation
 */
public class TsfAcoSimulator {
	
	/**
	 * Main method.
	 * @param args XML file name ex:"..\test_1.xml"
	 */
	public static void main(String[] args)  {
		XMLUtils xml = new XMLUtils(args[0]);
		Var var = xml.getV();	
		Simulator Sim = new Simulator(var);
	}
}