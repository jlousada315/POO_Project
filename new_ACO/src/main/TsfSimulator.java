package main;

//import aco_sim.*;
import xml_utils.*;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;



public class TsfSimulator {
	public static void main(String[] args)  {
		
		XMLUtils xml = new XMLUtils(args[0]);
		Var var = xml.getV();
		
		//Simulator Sim = new Simulator(var) ;
	}
}