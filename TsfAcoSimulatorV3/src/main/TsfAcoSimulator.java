package main;

import xml_utils.*;
import simulation.Simulator;

public class TsfAcoSimulator {
	public static void main(String[] args)  {

		XMLUtils xml = new XMLUtils(args[0]);
		Var var = xml.getV();
		
		Simulator sim = new Simulator(var);

		/*Simulator sim = new Simulator(var);*/
	}
}
