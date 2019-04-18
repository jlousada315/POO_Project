package aco_sim;

public class Graph {
	//attributes
	int totalW = 0; //total weight of graph
	public int nbnodes;
	public int nestnode;
	public Node[] nodes;
	
	//constructor
	public Graph(int nbnodes, int nestnode, double[][] weights){
		//initialize attributes
		this.nbnodes = nbnodes;
		this.nestnode = nestnode;
		//create graph
		nodes = new Node[nbnodes];
		for(int i=0; i<nbnodes; i++) {
			nodes[i] = new Node(i+1);
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
	
	//sets and gets
	public int getNestnb() {
		return nestnode;
	}
	
	public Node getNest() {
		//gets nest node
		if(nodes[nestnode-1] != null ) {
			Node Nest = nodes[nestnode-1];
			return Nest;
		} else {
		    System.out.println("Nest doesnt exist");
		    return null;
		}
	}
	
	public void print() {
		for(int i=0; i<nbnodes; i++)
			nodes[i].print();
	}
}