package graph;

import java.util.*;

public class Node {
	//attributes
	final int nodeidx;
	final ArrayList<Edge> edges;
	
	//constructor
	Node(int nodeidx){
		this.nodeidx = nodeidx;
		edges = new ArrayList<Edge>();
	}
	
	//gets and sets	
	public Edge getEdge(Node node2) {
		for(int i=0; i<edges.size(); i++)
			if(edges.get(i).node2.equals(node2))
				return edges.get(i);
		throw new NoSuchElementException("Edge does not exists.");
	}
	
	public Edge getEdge(int i) {
		return edges.get(i);
	}
		
	public int getEdgesSize() {
		return edges.size();
	}
	
	public int getIdx() {
		return nodeidx;
	}

	void setEdge(Node node2, double weight) {
		edges.add(new Edge(this, node2, weight));
	}

	//methods
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
		System.out.println("Node " + nodeidx);
		for(int i=0; i<edges.size(); i++)
			edges.get(i).print();
	}
}


