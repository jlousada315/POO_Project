package graph;

import java.util.LinkedList;

import eventHandler.Ant;
import pec.PEC;

/**
 * Provides an interface used to represent a weighted Graph. 
 * Offers methods to manage the graph, to determine the 
 * movement of the ants, and to control the levels 
 * of pheromones in the edges. 
 */
public interface IGraph {
	
	//returns nest node
	/**
	 * Returns nest node if there is any, otherwise 
	 * it returns NULL.
	 * @return totalW Total weight of the graph.
	 */
	Node getNest();
	/**
	 * Returns number of nodes.
	 * @return Number of nodes.
	 */
	int getSize();
	
	/**
	 * Get node.
	 * @param nodeidx Index of returned node.
	 * @return  Node.
	 */
	Node getNode(int nodeidx);
	
	/**
	 * Calculates probability of next nodes.
	 * 
	 * @param A Ant.
	 * @return probability Probability of moving to each adjacent edge.
	 */
	Double[] calculateProb(Ant A);
	
	/**
	 * Calculates the next Node index according 
	 * to the ordered probability vector.
	 * @param A Ant.
	 * @return Node index.
	 */
	int nextNode(Ant A);
	
	/**
	 * If cycle is Hamiltonian, increase level of pheromones
	 * on the path.
	 * 
	 * @param path Hamiltonian cycle.
	 */
	void updatePheromones(LinkedList<Integer> path);
	
	/**
	 * Initiates evap events when Hamiltonian is found
	 * and pheromone levels are zero.
	 * @param path Hamiltonian cycle.
	 * @param pec PEC.
	 * @param timestamp Current time.
	 */
	void initEvap(LinkedList<Integer> path, PEC pec, double timestamp);
	
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

}