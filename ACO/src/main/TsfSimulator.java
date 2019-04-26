package main;

//import aco_sim.*;
import xml_utils.XMLUtils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

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
		/*Graph G = new Graph(nbnodes, nestnode, weight);
		
		Move m = new Move(alpha, beta, delta, plevel,timestamp);
		Evap e = new Evap(eta,rho,gamma);
		//Create ant starting in nest
		Ant A = new Ant(G);
		
		LinkedList<Node> N = m.nextNode(A);
		
		/*for(int i = 0; i< N.size();++i) {
			N.get(i).print();
		}*/
		/*
		A.resetPath();
		A.updatePath(m, e);
		A.updatePath(m, e);
		A.updatePath(m, e);
		A.updatePath(m, e);
		*/
	}
}