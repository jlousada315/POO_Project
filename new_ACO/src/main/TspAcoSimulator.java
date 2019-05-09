package main;

import xml_utils.*;

import simulation.Simulator;

/**
 * Solves the Traveling Salesman Problem problem, using the Ant Colony optimization algorithm.
 */
public class TspAcoSimulator {
	
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