package aco_sim;

import java.util.*;


public class Ant{
	
	LinkedList<Node> path;
	Move m;
	Graph G;
	
	public Ant(Graph G, Move m) {
		// TODO Auto-generated constructor stub
		path = new LinkedList<Node>();
		path.add(G.getNest());
		this.m = m;
		this.G = G;
	}

	public int tranverseProb() {
		
		Node Current = path.get(path.size()-1);
		double probability = 0;
		int index = 0;
		double ci = 1;
		double w[] = new double[Current.edges.size()];
		double cij[] = new double[w.length];
		
		for(int i = 0; i < Current.edges.size();++i) {
			w[i] = Current.edges.get(i).weight;
			
		}
		
		for(int k=0; k < w.length ; ++k) {
			ci += (m.alpha + m.plevel)/(m.beta + w[k]); 
		}
		
		for(int k=0; k < w.length ; ++k) {
			cij[k] = (m.alpha + m.plevel)/(m.beta + w[k]); 
			if(cij[k]/ci > probability) {
				probability = cij[k]/ci;
				index = k;
				} 
		}
	
		return index;
		//wrong code : must return node correspondent to that edge . 
	}
	
	public void updatePath() {
		;
	}
}
