package aco_sim;

import graph.*;

public class AcoNode extends Node {
	//constructor
	AcoNode(int nodeidx){
		super(nodeidx);
	}
	
	//rewrite setEdge
	void setEdge(Node node2, double weight) {
		edges.add(new AcoEdge(this, node2, weight));
	}
}
