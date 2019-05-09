package graph;

import java.util.LinkedList;

import eventHandler.ITraveller;
import pec.PEC;

/**
 * Provides an interface used to represent a weighted Graph. 
 * Offers methods to manage the graph, to determine the 
 * movement of the ants, and to control the levels 
 * of pheromones in the edges. 
 */
public interface IGraph {
	
	/**
	 * Returns number of nodes.
	 * @return Number of nodes.
	 */
	public int getSize();

	/**
	 * Return the weight of a given edge.
	 * @param n1 1st node of edge.
	 * @param n2 2nd node of edge.
	 * @return Edge weight.
	 */
	public double getEdgeWeigth(int n1 , int n2);
	
	/**
	 * Calculates the next Node index according 
	 * to the ordered probability vector.
	 * @param A ITraveller.
	 * @return Node index.
	 */
	int nextNode(ITraveller A);
	
	/**
	 * If cycle is Hamiltonian, increase level of pheromones
	 * on the path.
	 * 
	 * @param path Hamiltonian cycle.
	 */
	public void updatePheromones(LinkedList<Integer> path);
	
	/**
	 * Initiates evap events when Hamiltonian is found
	 * and pheromone levels are zero.
	 * @param path Hamiltonian cycle.
	 * @param pec PEC.
	 * @param timestamp Current time.
	 */
	public void initEvap(LinkedList<Integer> path, PEC pec, double timestamp);
	
	/**
	 * Evap execution over edge returns true if 
	 * current level of is greater than rho, 
	 * otherwise its false. 
	 * 
	 * @param e_ij Both node indexes from edge.
	 * @return boolean
	 */
	public boolean evapFromEdge(int[] e_ij);
	
	/**
	 * Returns the weight of a path.
	 * @param path Path.
	 * @return pathW Path Weight.
	 */
	public double getPathWeight(LinkedList<Integer> path);
	
	/**
	 * Updates best Hamiltonian cycle stored if new the new path's 
	 * weight is less than the previous best Hamiltonian cycle.
	 * @param path Hamiltonian cycle to be compared.
	 */
	public void updateHamiltonian(LinkedList<Integer> path);

	/**
	 * Returns best Hamiltonian cycle.
	 * @return bestHamiltonian Best Hamiltonian cycle.
	 */
	public LinkedList<Integer> getBestHamiltonian();

}