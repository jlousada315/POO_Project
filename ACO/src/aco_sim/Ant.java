package aco_sim;

import java.util.*;
import graph.*;

public class Ant{
	//attributes
	LinkedList<Node> path;
	final Graph G;
	final double gamma;
	
	//constructor
	public Ant(Graph G, double gamma) {
		// TODO Auto-generated constructor stub
		path = new LinkedList<Node>();
		path.add(G.getNest());
		this.G = G;
		this.gamma = gamma;
	}
	
	//gets and sets
	public LinkedList<Node> getPath(){
		return path;
	}
	
	//methods
	boolean isHamiltonian(Node X) {
		if(path.size()==G.getNbNodes() && X==G.getNest()){return true;}
		else {return false;}
	}
	
	public void printPath() {
		for(int i=0 ;i < path.size(); ++i ) {
			path.get(i).print();
		}
	}
	
	public String toString() {
		int arr[] = new int[path.size()];
		for(int i=0 ;i < path.size(); ++i ) {
			arr[i] = path.get(i).getIdx();
		}
	 return Arrays.toString(arr);
	}
	
	//resets path to Node to find another Hamiltonian cycle
	public void resetPath() {
		path.clear();
		path.add(G.getNest());
	}
	
	
	//Update's Ant's path according to rules.
	public void updatePath(int next_nodeidx) {
		if(isHamiltonian(G.getNodes()[next_nodeidx])) {
			updatePheromones();
			System.out.println("Hamiltonian Cycle Found with path = " + toString());
			resetPath();
		}else {
			path.add(G.getNodes()[next_nodeidx]);
		}
	}
	
	//if cycle is Hamiltonian, increase level of pheromones on the path
	public void updatePheromones() {
		double pathW = 0;
		for(int i=0; i<path.size()-1; i++) {
			pathW += path.get(i).getEdge(path.get(i+1)).getWeight();
		}
		double updateValue = gamma*G.getTotalW()/pathW;
		for(int i=0; i<path.size()-1; i++) 
			path.get(i).getEdge(path.get(i+1)).setPheromone(updateValue);
	}
}