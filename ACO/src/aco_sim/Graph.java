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
	
	//returns node
	public int getNestnb() {
		return nestnode;
	}
	
	//returns nest node
	public Node getNest() {
		if(nodes[nestnode-1] != null ) {
			Node Nest = nodes[nestnode-1];
			return Nest;
		}else {
		    System.out.println("nest doesnt exist");
		    return null;
		}
	}
	
	//prints graph containing all nodes
	public void print() {
		for(int i = 0; i<nbnodes;i++) {
			nodes[i].print();
		}
	}

}