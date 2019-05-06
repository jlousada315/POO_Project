package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import eventHandler.*;
import aco_tools.Prob;
import aco_tools.ValueComparator;
import xml_utils.Var;

public class Graph implements IGraph {
	//attributes
	double totalW;
	final int nestnode;
	final Node[] nodes;

	//constructor
	public Graph(int nbnodes, int nestnode, double[][] weights){
		this.nestnode = nestnode;
		//create graph
		nodes = new Node[nbnodes];
		for(int i=0; i<nbnodes; i++) {
			nodes[i] = new Node(i+1);
		}
		for(int i=0; i<nbnodes; i++)
			for(int j=i+1; j<nbnodes; j++) {
				if(weights[i][j]!=0) {
					nodes[i].setEdge(nodes[j],weights[i][j]);
					nodes[j].setEdge(nodes[i],weights[i][j]);
					totalW += weights[i][j];
				}
			}
	}

	//get total weight
	public double getTotalW() {
		return totalW;
	}

	public Node getNode(int nodeidx) {
		return nodes[nodeidx-1];
	}
	//get nest node
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
	
	public int getSize() {
		return nodes.length;
	}

	//toString method
	@Override
	public String toString() {
		String str = "Graph [" + nodes.length + " nodes]\n";
		for(int i = 0; i< nodes.length;i++) {
			str+= nodes[i].toString() + "\n";
		}
		return str;
	}

	//calculates prob of nextnodes
	public Double[] calculateProb(Var v, Ant A) {		
		Node Current = A.getPath().get(A.getPath().size()-1); // Current AcoNode
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

	//Calculates the following AcoNode
	public int nextNode(Var v,Ant A) {
		Node Current = A.getPath().get(A.getPath().size()-1); // Current AcoNode NOTE: implement getLast() in Ant !!!!!!
		LinkedList<Node> Adj = new LinkedList<Node>();  //List of Adjacent AcoNodes of Current AcoNode		
		int nbedges = Current.edges.size(); //number of edges adjacent to current AcoNode
		Integer adjindex[] = new Integer[nbedges];  
		Object index[] = new Object[nbedges];

		Double[] probability = calculateProb(v,A); // array of the probability of tranversing to next AcoNode

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

		LinkedList<Node> J1 = new LinkedList<Node>();	//List of non-visited Adjacent AcoNodes J to current AcoNode i
		Collection<Node> J = new ArrayList<Node>();		//Collection identical to J1
		Prob uniform = new Prob();

		//Fills collection with ordered AcoNodes 
		for(int i = 0; i < index.length; ++i) {
			J.add(getNode((int)index[i]));
		}

		J.removeAll(A.getPath());	//eliminates AcoNodes that have already been visited
		J1.addAll(J);	//Makes a copy of Collection to LinkedList	


		//Returns next AcoNode
		if(J1.isEmpty()) {		
			Node nextnode = Adj.get(uniform.uniformDist(Adj.size()));
			return nextnode.nodeidx;	//if J is empty, ant chooses uniformly between already visited adjacent AcoNodes  

		}else {
			Node nextnode = J1.getFirst();
			return nextnode.nodeidx;	//ant chooses non visited AcoNode with the highest probability
		}		
	}
		
	//if cycle is Hamiltonian, increase level of pheromones on the path
	public void updatePheromones(Var v, Ant A) {
		double pathW = 0;
		for(int i=0; i<A.getPath().size()-1; i++) {
			pathW += ((Edge)A.getPath().get(i).getEdge(A.getPath().get(i+1))).weight;
		}
		double updateValue = v.getGamma()*totalW/pathW;
		for(int i=0; i<A.getPath().size()-1; i++) { 
			((Edge)A.getPath().get(i).getEdge(A.getPath().get(i+1))).pheromone += updateValue;	
		}	
	}
	
	
}