package main;

import aco_sim.*;
import java.util.LinkedList;

public class TsfSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Fields*/
		int finalinst = 300;
		double plevel = 0.5;
		int antcolsize = 200; 
		double alpha = 1;
		double beta = 1;
		double delta = 1;
		double rho = 1;
		double eta = 1;
		
		int nbnodes = 5;
		int nestnode = 4;
		
		double[][] weight = new double[nbnodes][5];
		
		//Filling weight with zeros 
		for(int i=0; i< nbnodes; i++) {
		    for(int j=0; j< 5; j++) {
		        weight[i][j] = 0;
		    }
		}
		
		//1st Node
		weight[0][1] = 3;
		weight[0][2] = 6;
		weight[0][3] = 6;
		weight[0][4] = 2;
		
		//2nd Node
		weight[1][2] = 3;
		weight[1][3] = 2;
		weight[1][4] = 5;
		
		//4th Node
		weight[3][4] = 1;
		
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
		
		//
		
	}

}
