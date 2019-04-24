package aco_sim;
import java.util.*;

public class Node {
	//attributes
	int nodeidx;
	ArrayList<Edge> edges;
	
	//constructor
	Node(int nodeidx){
		//initialize attributes
		this.nodeidx = nodeidx;
		edges = new ArrayList<Edge>();
	}
	
	//methods
	void setEdge(Node node2, double weight) {
		//set new edge from this node to node2 with weight
		edges.add(new Edge(this, node2, weight));
	}
	
	public Edge getEdge(Node node2) {
		//gets the edge from this node to node2
		for(int i=0; i<edges.size(); i++)
			if(edges.get(i).node2.equals(node2))
				return edges.get(i);
		//if there's not such edge throw exception
		throw new NoSuchElementException("Edge does not exists.");
	}
	
	public double getEdgeWeight(Node node2) {
		//gets the weight from the edge from this node to node2
		return getEdge(node2).weight;
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(nodeidx);
	}
		
	@Override
	public boolean equals(Object x) {
	    if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Node that = (Node) x;
        return this.nodeidx == that.nodeidx;
	}
	
	//other methods
	public void print() {
		System.out.println("id: " + nodeidx);
		for(int i=0; i<edges.size(); i++)
			edges.get(i).print();
	}
}