package graph;

import java.util.Objects;

public class Edge {
	//attributes
	final Node node1;
	final Node node2;
	final double weight;
	double pheromone=0;
	
	//constructor
	Edge(Node node1, Node node2, double weight){
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight; 
	}
	
	//gets and sets
	public Node getNode2() {
		return node2;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getPheromone() {
		return pheromone;
	}

	public void setPheromone(double value) {
		if(value == 0) 
			pheromone = 0;
		else
			pheromone += value;
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
	void print() {
		System.out.print("[(" + node1.nodeidx);
		System.out.print("," + node2.nodeidx + ")");
		System.out.print(",w=" + weight);
		System.out.print(",p=" + pheromone + "]\n");
	}
}
