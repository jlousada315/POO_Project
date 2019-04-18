package aco_sim;

public class Graph {
	//attributes
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
			nodes[i] = new Node(i);
		}
		for(int i=0; i<nbnodes; i++)
			for(int j=i; j<nbnodes; j++) {
				if(weights[i][j]!=0)
					nodes[i].setEdge(nodes[j],weights[i][j]);
			}
	}
	
	//sets and gets
	public int getNestnb() {
		return nestnode;
	}
	
	public Node getNest() {
		if(nodes[nestnode-1] != null ) {
			Node Nest = nodes[nestnode-1];
			return Nest;
		} else {
		    System.out.println("nest doesnt exist");
		    return null;
		}
	}
	
	public Node getNode(int id) {
		if(nodes[id-1] != null && id > 0 && id < nbnodes) {
		return nodes[id-1];
		}else {
			System.out.println("invalid index");
			return null;
		}
	}
	
}