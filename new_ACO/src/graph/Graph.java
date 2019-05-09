package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import eventHandler.*;
import pec.PEC;
import aco_tools.Prob;
import aco_tools.ValueComparator;
import xml_utils.Var;

/**
 * Used to represent a weighted Graph. Stores the total 
 * weight of the graph, the nest node, an array with 
 * the associated nodes and a LinkedList with the best 
 * Hamiltonian path found. Also offers methods to manage 
 * the graph, to determine the movement of the ants, and 
 * to control the levels of pheromones in the edges. 
 */
public class Graph implements IGraph {
	//attributes
		Var v;
		double totalW;
		final int nestnode;
		final Node[] nodes;
		public LinkedList<Integer> bestHamiltonian = null;

		/**
		 * Constructor of Graph .
		 * @param v Variables needed to create the graph.
		 */
		public Graph(Var v){
			this.v = v;
			this.nestnode = v.getNestnode();
			int nbnodes = v.getNbnodes();
			//create graph
			nodes = new Node[nbnodes];
			for(int i=0; i<nbnodes; i++) {
				nodes[i] = new Node(i+1);
			}
			double[][] weights = v.getWeight();
			for(int i=0; i<nbnodes; i++)
				for(int j=i+1; j<nbnodes; j++) {
					if(v.getWeight()[i][j]!=0) {
						nodes[i].setEdge(nodes[j],weights[i][j]);
						nodes[j].setEdge(nodes[i],weights[i][j]);
						totalW += weights[i][j];
					}
				}
		}

	/**
	 * Get total weight of the graph.
	 * @return totalW total weight of the graph
	 */
	public double getTotalW() {
		return totalW;
	}

	
	
	@Override
	public Node getNode(int nodeidx) {
		return nodes[nodeidx-1];
	}
	
	/**
	 * Returns nest node if there is any, otherwise 
	 * it returns NULL.
	 * @return Nest Nest node.
	 */
	public Node getNest() {
		//gets nest node
		if(nodes[nestnode-1] != null ) {
			Node Nest = nodes[nestnode-1];
			return Nest;
		} else {
			System.out.println("Nest doesnt exist");
			return null;
		}
	}
	
	/**
	 * Returns number of nodes.
	 * @return Number of nodes.
	 */
	public int getSize() {
		return nodes.length;
	}

	@Override
	public String toString() {
		String str = "Graph [" + nodes.length + " nodes]\n";
		for(int i = 0; i< nodes.length;i++) {
			str+= nodes[i].toString() + "\n";
		}
		return str;
	}
	
	/**
	 * Return the weight of a given edge.
	 * @param n1 1st node of edge.
	 * @param n2 2nd node of edge.
	 * @return Edge weight.
	 */
	public double getEdgeWeigth(int n1 , int n2){
		return nodes[n1-1].getEdge(nodes[n2-1]).weight;
	}
	
	/**
	* Return the pheromone level of a given edge.
	 * @param n1 1st node of edge.
	 * @param n2 2nd node of edge.
	 * @return Edge pheromone level.
	 */
	public double getEdgePheromones(int n1 , int n2){
		return nodes[n1-1].getEdge(nodes[n2-1]).pheromone;
	}
	
	/**
	 * Calculates probability of next nodes.
	 * 
	 * @param A Ant.
	 * @return probability Probability of moving to each adjacent edge.
	 */
	public Double[] calculateProb(Ant A) {		
		Node Current = nodes[A.getLast()-1]; // Current Node
		LinkedList<Node> Adj = new LinkedList<Node>();  //List of Adjacent AcoNodes of Current AcoNode
		double ci = 0;	//Normalization Constant
		double w[] = new double[Current.edges.size()]; //Array of weights
		double cij[] = new double[w.length]; //Proportional to Probability
		Double probability[] = new Double[w.length]; //probability of tranversing

		//Array with each edge with weight and Adjacent list of AcoNodes 
		for(int i = 0; i < Current.edges.size();++i) {
			w[i] = Current.edges.get(i).weight;
			Adj.add(Current.edges.get(i).node2);
		}

		//Probability Calculation
		for(int k=0; k < w.length ; ++k) {
			cij[k] = (v.getAlpha() + v.getPlevel())/(v.getBeta() + w[k]); 	 
			ci += (v.getAlpha() + v.getPlevel())/(v.getBeta() + w[k]); 
		}	

		//Probabilities are stored in array.
		Prob uniform = new Prob();
		for(int k=0; k < w.length ; ++k) {
			probability[k] = (uniform.uniformDist(100))*cij[k]/ci; 
		}

		return probability;
	}

	/**
	 * Calculates the next Node index according 
	 * to the ordered probability vector.
	 * @param A Ant.
	 * @return Node index.
	 */
	public int nextNode(Ant A) {
		Node Current = nodes[A.getLast()-1]; // Current Node
		LinkedList<Node> Adj = new LinkedList<Node>();  //List of Adjacent AcoNodes of Current AcoNode		
		int nbedges = Current.getEdgesSize(); //number of edges adjacent to current AcoNode
		Integer adjindex[] = new Integer[nbedges];  
		Object index[] = new Object[nbedges];

		Double[] probability = calculateProb(A); // array of the probability of tranversing to next AcoNode

		//Fills List Adj with adjacent AcoNodes 
		for(int i = 0 ; i < nbedges ; ++i) {
			Adj.add(Current.edges.get(i).node2);			
			adjindex[i] = Adj.get(i).nodeidx;
		}
		
		//Map to sort AcoNode index , by descendent probability 
		Map<Integer,Double> unsortMap = new HashMap<Integer,Double>();
		ValueComparator bvc = new ValueComparator(unsortMap); 
		Map<Integer,Double> treeMap = new TreeMap<Integer,Double>(bvc);

		//Fills unsortMap with Key -> index of adjacent AcoNodes ; Value -> Probabilty 
		for(int k=0; k < nbedges ; ++k) {
			unsortMap.put(adjindex[k],probability[k]);
		}

		//Map is sorted by highest value to lowest
		treeMap.putAll(unsortMap);
		index = treeMap.keySet().toArray();

		LinkedList<Integer> J1 = new LinkedList<Integer>();	//List of non-visited Adjacent AcoNodes J to current AcoNode i
		Collection<Integer> J = new ArrayList<Integer>();		//Collection identical to J1
		Prob uniform = new Prob();
		
		//Fills collection with ordered AcoNodes 
		for(int i = 0; i < index.length; ++i) {
			J.add((int)index[i]);
		}

		J.removeAll(A.getPath());	//eliminates AcoNodes that have already been visited
		J1.addAll(J);	//Makes a copy of Collection to Linked

		//Returns next AcoNode
		if(J1.isEmpty()) {		
			Node nextnode = Adj.get(uniform.uniformDist(Adj.size()));
			return nextnode.nodeidx;	//if J is empty, ant chooses uniformly between already visited adjacent AcoNodes  

		}else {
			return J1.getFirst();	//ant chooses non visited AcoNode with the highest probability
		}		
	}
		
	/**
	 * If cycle is Hamiltonian, increase level of pheromones
	 * on the path.
	 * 
	 * @param path Hamiltonian cycle.
	 */
	public void updatePheromones(LinkedList<Integer> path) {
		double pathW = getPathWeight(path);
		double updateValue = v.getPlevel()*totalW/pathW;
		//System.out.println("New Value: " + updateValue);
		int i;
		for(i=0; i<path.size()-1; i++) { 
			((Edge)nodes[path.get(i)-1].getEdge(nodes[path.get(i+1)-1])).pheromone += updateValue;	
		}
		((Edge)nodes[path.get(i)-1].getEdge(nodes[path.get(0)-1])).pheromone += updateValue;	
	}
		
	/**
	 * Initiates evap events when Hamiltonian is found
	 * and pheromone levels are zero.
	 * @param path Hamiltonian cycle.
	 * @param pec PEC.
	 * @param timestamp Current time.
	 */
	public void initEvap(LinkedList<Integer> path, PEC pec, double timestamp) {
		int[] aux = new int[2];
		for(int i=0; i<path.size()-1; i++) {
			aux[0] = path.get(i);
			aux[1] = path.get(i+1);
			//check if evaporation event is going on
			if(getEdgePheromones(aux[0], aux[1]) == 0) {
				pec.addEvPEC(new Evap(aux, timestamp+Prob.expRand(v.getEta())));
			}
		}
		aux[0] = aux[1];
		aux[1] = path.get(0);
		if(getEdgePheromones(aux[0], aux[1]) == 0) {
			pec.addEvPEC(new Evap(aux, Prob.expRand(v.getEta())));
		}
	}
		
	/**
	 * Evap execution over edge returns true if 
	 * current level of is greater than rho, 
	 * otherwise its false. 
	 * 
	 * @param e_ij Both node indexes from edge.
	 * @return boolean
	 */
	public boolean evapFromEdge(int[] e_ij){
		if(nodes[e_ij[0]-1].getEdge(nodes[e_ij[1]-1]).pheromone>v.getRho()) {
			nodes[e_ij[0]-1].getEdge(nodes[e_ij[1]-1]).pheromone -= v.getRho();
			return true;
		}
		nodes[e_ij[0]-1].getEdge(nodes[e_ij[1]-1]).pheromone = 0;		
		return false;
	}

	/**
	 * Returns the weight of a path.
	 * @param path Path.
	 * @return pathW Path Weight.
	 */
	public double getPathWeight(LinkedList<Integer> path) {
		double pathW = 0;
		for(int i=0; i<path.size()-1; i++) {
			pathW += ((Edge)nodes[path.get(i)-1].getEdge(nodes[path.get(i+1)-1])).weight;
		}		
		return pathW;
	}
	
	/**
	 * Updates best Hamiltonian cycle stored if new the new path's 
	 * weight is less than the previous best Hamiltonian cycle.
	 * @param path Hamiltonian cycle to be compared.
	 */
	@SuppressWarnings("unchecked")
	public void updateHamiltonian(LinkedList<Integer> path) {
		if(bestHamiltonian==null || getPathWeight(path)<getPathWeight(bestHamiltonian)) {
			bestHamiltonian = (LinkedList<Integer>)path.clone();
		}
	}
	
	/**
	 * Returns best Hamiltonian cycle.
	 * @return bestHamiltonian Best Hamiltonian cycle.
	 */
	public LinkedList<Integer> getBestHamiltonian() {
		return bestHamiltonian;
	}
}