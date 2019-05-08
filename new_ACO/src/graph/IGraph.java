package graph;

import java.util.LinkedList;

import eventHandler.Ant;
import pec.PEC;

public interface IGraph {
	
	//returns nest node
	Node getNest();
	int getSize();
	Node getNode(int nodeidx);
	
	//return total weigth of path
	//double pathWeigth(int[] path);
	
	//calculates prob of nextnodes
	Double[] calculateProb(Ant A);
	
	//returns index of next node.
	int nextNode(Ant A);
	
	//updates Pheromone level.
	void updatePheromones(LinkedList<Integer> path);
	
	void initEvap(LinkedList<Integer> path, PEC pec, double timestamp);
	
	public double getPathWeight(LinkedList<Integer> path);
	
	public void updateHamiltonian(LinkedList<Integer> path);

}