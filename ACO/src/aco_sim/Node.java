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
	
	void setEdge(Node node2, double weight) {
		edges.add(new Edge(this, node2, weight));
	}
	
	//sets and gets
	void setIdx(int value) {
		nodeidx = value;
	}
	
	int getIdx() {
		return nodeidx;
	}
	
	//methods
	
	
	
	//other methods
	public void print() {
		System.out.println("id: " + nodeidx);
		for(int i=0; i<edges.size(); i++)
			edges.get(i).print();
	}
}
