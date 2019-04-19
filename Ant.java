package aco_sim;

import java.util.*;

public class Ant{
	//attributes
	LinkedList<Node> path;
	Graph G;
	
	//constructor
	public Ant(Graph G) {
		// TODO Auto-generated constructor stub
		path = new LinkedList<Node>();
		path.add(G.getNest());
		this.G = G;
	}
	
	//flag: 1 if there is a duplicate, 2 if hamiltonian cycle is complete and 0 o.w.
	int duplicate(Node X) {
		for(int i = 0; i < path.size(); ++i) {
			if(X == path.get(i) && path.size()!=G.nbnodes) {return 1;}
		}
		if(path.size()==G.nbnodes && X==G.getNest()){return 2;}
		else return 0;
	}
		
	public void updatePath(Move m) {
		LinkedList<Node> aux = new LinkedList<Node>();
		Prob uniform = new Prob();
		aux = m.nextNode(this);
		
		//if there is only one option to tranverse
		if(aux.size()==1 && duplicate(aux.getFirst()) == 0) {
			path.add(aux.getFirst());
		}else if(duplicate(aux.getFirst()) == 1) {
			if(path!= null) {path.removeLast();}
		}
		
		//if there are several edges with the same probability, a new node is chosen with an uniform distribution
		if(aux.size()!=1 && duplicate(aux.getFirst()) == 0) {
			path.add(aux.get(uniform.uniformDist(aux.size())));
		}
		
	}
}