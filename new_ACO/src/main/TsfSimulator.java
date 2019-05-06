package main;

//import aco_sim.*;
import xml_utils.XMLUtils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import aco_sim.*;


public class TsfSimulator {
	public static void main(String[] args)  {

		/*Loading and validation of XML file*/
		boolean vali = true;
		try {
			vali = XMLUtils.validateWithDTDUsingDOM(args[0]);
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println(vali);

		/*Initialization*/
		double finalinst = XMLUtils.getFinalinst();
		System.out.println("finalinst = " + finalinst);
		int antcolsize = XMLUtils.getAntCol(); 
		System.out.println("antcolsize = " + antcolsize);
		double plevel = XMLUtils.getPLevel();
		System.out.println("plevel = " + plevel);
		int nbnodes = XMLUtils.getNbNodes();
		System.out.println("nbnodes = " + nbnodes);
		int nestnode = XMLUtils.getNestNode();
		System.out.println("nestnode = " + nestnode);
		double aux[] = XMLUtils.getGreek();
		double alpha = aux[0];
		System.out.println("alpha = " + alpha);
		double beta = aux[1];
		System.out.println("beta = " + beta);
		double delta = aux[2];
		System.out.println("delta = " + delta);
		double eta = aux[3];
		System.out.println("eta = " + eta);
		double rho = aux[4];
		System.out.println("rho = " + rho);
		double[][] weight = XMLUtils.getWeights(); 
		for(int i=0; i< nbnodes; i++) {
			System.out.println( weight[i][0] +" "+ weight[i][1] +" "+ weight[i][2] +" "+ weight[i][3] +" "+ weight[i][4] );
		}

		
		/*Simulation*/
		/*Test without PEC*/
		/*
		AcoGraph G = new AcoGraph(nbnodes, nestnode, weight);
		Ant A = new Ant(G,gamma);

		double timestamp = 0;
		Move m = new Move(A,timestamp ,alpha, beta, delta, plevel);
		
		
		
		
		A.resetPath();

		int next=0;
		for(int i = 0; i < 20;++i) {
			next = m.nextAcoNode();
			A.updatePath(next);
			System.out.println("next node -> " + next);	
			System.out.println("updated path -> " + A.toString());
		}	
		*/
		
		Simulator Sim = new Simulator(nbnodes,nestnode,weight, alpha, beta, delta, eta, rho, plevel, antcolsize, finalinst) ;
		Sim.run();
	}
}