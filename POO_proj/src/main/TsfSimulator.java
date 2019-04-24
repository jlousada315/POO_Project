package main;

import aco_sim.*;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

public class TsfSimulator {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*Loading and validating XML file*/
		boolean vali = XMLUltils.validateWithDTDUsingDOM(args[0]);
		System.out.println(vali);
		
		/*Fields*/
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
		
		Graph G = new Graph(nbnodes, nestnode, weight);
		
		Node Nest = G.getNest();
		Nest.print();
		
		Move m = new Move(alpha, beta, delta, plevel);
		//Create ant starting in nest
		Ant A = new Ant(G);
		
		//test if next node is correct
		LinkedList<Node> next = m.nextNode(A);
		for(int i = 0 ; i < next.size();++i) {
			next.get(i).print();
		}
		next = m.nextNode(A);
		for(int i = 0 ; i < next.size();++i) {
			next.get(i).print();
		}
		
	}

}
