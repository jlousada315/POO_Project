package graph;

import java.util.LinkedList;
import pec.PEC;

public interface IGraph {
	//returns nest node
	int getNest();
	int getSize();
	
	//calculates prob of nextnodes
	Double[] calculateProb(int current, LinkedList<Integer> adj);
	
	//returns index of next node.
	int nextNode(LinkedList<Integer> path);
	
	//updates Pheromone level.
	void updatePheromones(LinkedList<Integer> path);
	
	//init evap moves
	void initEvap(LinkedList<Integer> path, PEC pec);
	
	//give edge from a[0] to a[1]
	boolean evapFromEdge(int[] a);
	
	//toString
	String toString();
}