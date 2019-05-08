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

public class Graph implements IGraph {
	//attributes
	Var v;
	double[][] weights;
	double[][] pheromones;
	double totalW;
	final int nestnode;
	
	//constructor
	public Graph(Var v){
		this.v = v;
		this.nestnode = v.getNestnode();
		int nbnodes = v.getNbnodes();
		//create graph
		this.weights = v.getWeight();
		this.pheromones = new double[nbnodes][nbnodes];
		//add inverse matrix
		for(int i=0; i<nbnodes; i++)
			for(int j=i+1; j<nbnodes; j++) {
				weights[j][i] = weights[i][j];
				totalW += weights[i][j];
			}
	}

	//get nest node
	@Override
	public int getNest() {
		return nestnode;
	}
	
	//get number of nodes
	@Override
	public int getSize() {
		return weights.length;
	}

	public double getEdgeWeigth(int n1 , int n2){
		return weights[n1][n2];
	}
	
	//calculates prob of nextnodes
	public Double[] calculateProb(int current, LinkedList<Integer> adj) {		
		LinkedList<Integer> Adj = adj;  //List of Adjacent AcoNodes of Current AcoNode
		double ci = 0;	//Normalization Constant
		double w[] = new double[Adj.size()]; //Array of weights
		double cij[] = new double[w.length]; //Proportional to Probability
		Double probability[] = new Double[w.length]; //probability of tranversing

		//Array with each edge with weight and Adjacent list of AcoNodes 
		for(int i = 0; i < Adj.size();++i) {
			w[i] = weights[current][Adj.get(i)];
		}

		//Probability Calculation
		for(int k=0; k < w.length ; ++k) {
			cij[k] = (v.getAlpha() + v.getPlevel())/(v.getBeta() + w[k]); 	 
			ci += (v.getAlpha() + v.getPlevel())/(v.getBeta() + w[k]); 
		}	

		//Probabilities are stored in array.
		for(int k=0; k < w.length ; ++k) {
			probability[k] = (Prob.uniformDist(100))*cij[k]/ci; 
		}

		return probability;
	}

	//Calculates the next Node 
	public int nextNode(LinkedList<Integer> path) {
		int Current = path.getLast(); // Current Node
		LinkedList<Integer> Adj = new LinkedList<Integer>();  //list of Adjacent Nodes of Current	
		for(int i=0; i<weights.length-1; i++) {
			if(weights[Current][i]!=0 && i+1!=Current)
				Adj.add(i+1);
		}
		int nbedges = Adj.size(); //number of edges adjacent to current AcoNode
		Object index[] = new Object[nbedges];

		Double[] probability = calculateProb(Current, Adj); // array of the probability of tranversing to next AcoNode

		//Map to sort AcoNode index , by descendent probability 
		Map<Integer,Double> unsortMap = new HashMap<Integer,Double>();
		ValueComparator bvc = new ValueComparator(unsortMap); 
		Map<Integer,Double> treeMap = new TreeMap<Integer,Double>(bvc);

		//Fills unsortMap with Key -> index of adjacent AcoNodes ; Value -> Probabilty 
		for(int k=0; k < nbedges ; ++k) {
			unsortMap.put(Adj.get(k),probability[k]);
		}

		//Map is sorted by highest value to lowest
		treeMap.putAll(unsortMap);
		index = treeMap.keySet().toArray();

		LinkedList<Integer> J1 = new LinkedList<Integer>();	//List of non-visited Adjacent AcoNodes J to current AcoNode i
		Collection<Integer> J = new ArrayList<Integer>();		//Collection identical to J1
		
		//Fills collection with ordered AcoNodes 
		for(int i = 0; i < index.length; ++i) {
			J.add((int)index[i]);
		}

		J.removeAll(path);	//eliminates AcoNodes that have already been visited
		J1.addAll(J);	//Makes a copy of Collection to Linked

		//Returns next AcoNode
		if(J1.isEmpty()) {		
			int nextnode = Adj.get(Prob.uniformDist(Adj.size()));
			return nextnode; //if J is empty, ant chooses uniformly between already visited adjacent AcoNodes  

		} else {
			return J1.getFirst(); //ant chooses non visited AcoNode with the highest probability
		}		
	}
		
	//if cycle is Hamiltonian, increase level of pheromones on the path
	public void updatePheromones(LinkedList<Integer> path) {
		double pathW = 0;
		for(int i=0; i<path.size()-1; i++) {
			pathW += getEdgeWeigth(path.get(i), path.get(i+1));
		}
		double updateValue = v.getPlevel()*totalW/pathW;
		System.out.println("New Value" + updateValue);
		for(int i=0; i<path.size()-1; i++) { 
			pheromones[path.get(i)][path.get(i+1)] += updateValue;	
		}	
	}
	
	//init Evap moves
	public void initEvap(LinkedList<Integer> path, PEC pec) {
		int[] aux = new int[2];
		for(int i=0; i<path.size()-1; i++) {
			aux[0] = path.get(i);
			aux[1] = path.get(i+1);
			pec.addEvPEC(new Evap(aux, Prob.expRand(v.getEta())));
		}
	}
		
	//evap execution over edge
	public boolean evapFromEdge(int[] a){
		if(pheromones[a[0]][a[1]]>v.getRho()) {
			pheromones[a[0]][a[1]] -= v.getRho();
			return true;
		}
		pheromones[a[0]][a[1]] = 0;		
		return false;
	}
	
	//to string method
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Weights:\n");
		for(int i=0; i<weights.length; i++) {
			for(int j=0; j<weights.length; j++) 
				str.append(weights[i][j] + " ");
			str.append("\n");
		}
		str.append("Pheromones:\n");
		for(int i=0; i<pheromones.length; i++) {
			for(int j=0; j<pheromones.length; j++) 
				str.append(pheromones[i][j] + " ");
			str.append("\n");
		}
		return str.toString();		
	}
}