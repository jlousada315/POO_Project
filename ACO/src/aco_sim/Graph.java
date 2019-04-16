package projectoPOO;

abstract class Graph {
	//attributes
	int nbnodes;
	int nestnode;
	Node[] nodes;
	
	//constructor
	Graph(int nbnodes, int nestnode, double[][] weights){
		//initialize attributes
		this.nbnodes = nbnodes;
		this.nestnode = nestnode;
		//create graph
		nodes = new Node[nbnodes];
		for(int i=0; i<nbnodes; i++) {
			nodes[i] = new Node(i);
		}
		for(int i=0; i<nbnodes; i++)
			for(int j=0; j<nbnodes; j++) {
				nodes[i].setEdge(nodes[j],weights[i][j]);
			}

	}
}