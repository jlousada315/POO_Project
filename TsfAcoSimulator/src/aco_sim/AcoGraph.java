package aco_sim;

import graph.*;

public class AcoGraph implements Graph {
	//attributes
	double totalW;
	final int nbnodes;
	final int nestnode;
	final AcoNode[] nodes;
	
	//constructor
	public AcoGraph(int nbnodes, int nestnode, double[][] weights){
		this.nbnodes = nbnodes;
		this.nestnode = nestnode;
		//create graph
		nodes = new AcoNode[nbnodes];
		for(int i=0; i<nbnodes; i++) {
			nodes[i] = new AcoNode(i+1);
		}
		for(int i=0; i<nbnodes; i++)
			for(int j=i+1; j<nbnodes; j++) {
				if(weights[i][j]!=0) {
					nodes[i].setEdge(nodes[j],weights[i][j]);
					nodes[j].setEdge(nodes[i],weights[i][j]);
					totalW += weights[i][j];
				}
			}
	}

	//get nodes list
	@Override
	public AcoNode[] getNodes() {
		return nodes;
	}

	//get number of nodes
	@Override
	public int getNbNodes() {
		return nbnodes;
	}

	//get total weight
	public double getTotalW() {
		return totalW;
	}
	
	//get nest node
	public AcoNode getNest() {
		//gets nest node
		if(nodes[nestnode-1] != null ) {
			AcoNode Nest = nodes[nestnode-1];
			return Nest;
		} else {
		    System.out.println("Nest doesnt exist");
		    return null;
		}
	}
	
	//toString method
	@Override
	public String toString() {
		String str = "Graph [" + nbnodes + " nodes]\n";
		for(int i = 0; i<nbnodes;i++) {
			str+= nodes[i].toString() + "\n";
		}
		return str;
	}
}