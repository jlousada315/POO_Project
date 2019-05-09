package graph;

import java.util.LinkedList;

import eventHandler.Ant;
import pec.PEC;

public interface IGraph {
	
	//returns nest node
	public int getSize();
	
	public double getEdgeWeigth(int n1 , int n2);

	//returns index of next node.
	public int nextNode(Ant A);
	
	//updates Pheromone level.
	public void updatePheromones(LinkedList<Integer> path);
	
	public void initEvap(LinkedList<Integer> path, PEC pec, double timestamp);
	
	public boolean evapFromEdge(int[] e_ij);
	
	public double getPathWeight(LinkedList<Integer> path);
	
	public void updateHamiltonian(LinkedList<Integer> path);
	
	public LinkedList<Integer> getBestHamiltonian();
}