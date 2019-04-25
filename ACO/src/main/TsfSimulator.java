package main;

import aco_sim.*;
import java.util.LinkedList;

public class TsfSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Loading and validation of XML file*/
		boolean vali = XMLUltils.validateWithDTDUsingDOM(args[0]);
		System.out.println(vali);
	
		/*Initialization*/
		double finalinst = XMLUltils.getFinalinst();
		System.out.println("finalinst = " + finalinst);
		int antcolsize = XMLUltils.getAntCol(); 
		System.out.println("antcolsize = " + antcolsize);
		double plevel = XMLUltils.getPLevel();
		System.out.println("plevel = " + plevel);
		int nbnodes = XMLUltils.getNbNodes();
		System.out.println("nbnodes = " + nbnodes);
		int nestnode = XMLUltils.getNestNode();
		System.out.println("nestnode = " + nestnode);
		double aux[] = XMLUltils.getGreek();
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
		double[][] weight = XMLUltils.getWeights(); 
		for(int i=0; i< nbnodes; i++) {
		    System.out.println( weight[i][0] +" "+ weight[i][1] +" "+ weight[i][2] +" "+ weight[i][3] +" "+ weight[i][4] );
		}
		
		/*Simulation*/
		Graph G = new Graph(nbnodes, nestnode, weight);
		
		Move m = new Move(alpha, beta, delta, plevel,timestamp);
		Evap e = new Evap(eta,rho,gamma);
		//Create ant starting in nest
		Ant A = new Ant(G);
		
		LinkedList<Node> N = m.nextNode(A);
		
		/*for(int i = 0; i< N.size();++i) {
			N.get(i).print();
		}*/
		
		A.resetPath();
		A.updatePath(m, e);
		A.updatePath(m, e);
		A.updatePath(m, e);
		A.updatePath(m, e);
		
	}

}
