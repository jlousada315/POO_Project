package aco_sim;

import java.util.*;

public class Ant {
	//attributes
	LinkedList<AcoNode> path;
	AcoGraph G;
	double gamma;

	//constructor
	public Ant(AcoGraph G, double gamma) {
		path = new LinkedList<AcoNode>();
		path.add(G.getNest());
		this.G = G;
		this.gamma = gamma;
	}

	LinkedList<AcoNode> getPath() {
		return path;
	}

	//resets path to Node to find another Hamiltonian cycle
	void resetPath() {
		path.clear();
		path.add(G.getNest());
	}
	
	//checks if path contains a Hamiltonian cycle
	boolean isHamiltonian(AcoNode X) {
		if(path.size()==G.nbnodes && X.equals(G.getNest())){return true;}
		else {return false;}
	}

	//checks if there are duplicated nodes in path
	boolean hasDuplicate() {
		for(int i = 0; i < path.size() ; ++i) {
			for(int j = 0; j < path.size() ; ++j) {
				if(path.get(i).equals(path.get(j)) && i!=j ) {
					return true;
				}
			}
		}
		return false;
	}

	//update's Ant's path according to rules.
	void updatePath(int next_nodeidx) {
		if(hasDuplicate()) {
			int i=path.size()-1;
			while(path.getLast().equals(path.get(i-1)) != true) {
				--i;
			}
			for(int j = path.size()-1 ; j >= i;--j) {
				path.remove(j);
			}
		}else if(isHamiltonian(G.nodes[next_nodeidx-1])) {
			updatePheromones();
			System.out.println("Hamiltonian Cycle Found with path = " + toString());
			resetPath();
		}else if(G.getNest().equals(G.nodes[next_nodeidx-1]) != true && !hasDuplicate()){
			path.add(G.nodes[next_nodeidx-1]);
		} 

	}

	//if cycle is Hamiltonian, increase level of pheromones on the path
	void updatePheromones() {
		double pathW = 0;
		for(int i=0; i<path.size()-1; i++) {
			pathW += ((AcoEdge)path.get(i).getEdge(path.get(i+1))).getWeight();
		}
		double updateValue = gamma*G.totalW/pathW;
		for(int i=0; i<path.size()-1; i++) 
			((AcoEdge)path.get(i).getEdge(path.get(i+1))).setPheromone(updateValue);
	}

	//prints path 
	public void printPath() {
		for(int i=0 ;i < path.size(); ++i ) {
			System.out.println(path.get(i).toString());
		}
	}
	
	//toString method
	@Override
	public String toString() {
		int arr[] = new int[path.size()];
		for(int i=0 ;i < path.size(); ++i ) {
			arr[i] = path.get(i).getIdx();
		}
		return Arrays.toString(arr);
	}
}