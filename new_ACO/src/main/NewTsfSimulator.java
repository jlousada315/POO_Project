package main;

import xml_utils.*;

import eventHandler.Ant;
import graph.Graph;



public class NewTsfSimulator {
	public static void main(String[] args)  {

		XMLUtils xml = new XMLUtils(args[0]);
		Var var = xml.getV();

		Graph G = new Graph(var);
		Ant A = new Ant(var.getNestnode());
		System.out.print(" nest" + var.getNestnode() + "\n");

		int next_node;
		for(int i = 0;i<100;++i) {
			next_node = G.nextNode(var, A);
			A.updatePath(next_node, G, var);
			System.out.print("path " + A.toString() + "\n");
		}


		//Simulator Sim = new Simulator(var) ;
	}
}