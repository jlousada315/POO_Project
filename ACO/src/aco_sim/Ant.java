package aco_sim;

import java.util.*;

public class Ant{
	
	LinkedList<Node> path;
	Graph G;
	
	public Ant(Graph G) {
		// TODO Auto-generated constructor stub
		path = new LinkedList<Node>();
		path.add(G.getNest());
		this.G = G;
	}
	
	LinkedList<Node> getPath(){
		return path;
	}
	
	//flag that is 1 if there is a duplicate, 2 if hamiltonian cycle is complete and 0 o.w.
	int duplicate(Node X) {
		for(int i = 0; i < path.size(); ++i) {
			if(X == path.get(i) && path.size()!=G.nbnodes) {return 1;}
		}
			if(path.size()==G.nbnodes && X==G.getNest()){return 2;}
			else {return 0;}
	}
	
	
	public void printPath() {
		for(int i=0 ;i < path.size(); ++i ) {
			path.get(i).print();
		}
	}
	
	public String toString() {
		int arr[] = new int[path.size()];
		for(int i=0 ;i < path.size(); ++i ) {
			arr[i] = path.get(i).nodeidx;
		}
	 return Arrays.toString(arr);
	}
	
	//resets path to Node to find another Hamiltonian cycle
	public void resetPath() {
		path.clear();
		path.add(G.getNest());
	}
	
	//Update's Ant's path according to rules.
	public void updatePath(Move m, Evap e) {
		LinkedList<Node> aux = new LinkedList<Node>();
		Prob uniform = new Prob();
			
		int i = 0;
		while(i < 40) {

		aux = m.nextNode(this);
		
		for(int k = 0; k < aux.size(); ++k) {
			if(duplicate(aux.get(k)) == 0){
				path.add(aux.get(k));
				break;
			}
		}
		
		for(int k = 0; k < aux.size(); ++k) {
			if(duplicate(aux.get(k)) == 2){		
				e.updatePheromones(this);
				System.out.println("Hamiltonian Cycle Found with path = " + toString());
				resetPath();
			}
		}
		
	++i;	
	}
	}

}
