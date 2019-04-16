package aco_sim;

public abstract class Graph {
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
}