package aco_sim;

import java.util.Objects;

public class Edge {
	//attributes
	Node node1;
	Node node2;
	double weight;
	double pheromone=0;
	
	//constructor
	Edge(Node node1, Node node2, double weight){
		//initialize attributes
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight; 
	}
	
	//methods
	@Override 
	public int hashCode() {
		return Objects.hash(node1, node2, weight);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
        if (!(obj instanceof Edge)) return false;
        Edge newEdge = (Edge) obj;
        return (this.node1.equals(newEdge.node1)
        		&& this.node2.equals(newEdge.node2));
	}
	
	//other methods
	public void print() {
		System.out.print("[(" + node1.nodeidx);
		System.out.print("," + node2.nodeidx + ")");
		System.out.print(",w=" + weight);
		System.out.print(",p=" + pheromone + "]\n");
	}
}
