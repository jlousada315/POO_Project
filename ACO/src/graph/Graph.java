package graph;

public class Graph {
	//attributes
	double totalW;
	final int nbnodes;
	final int nestnode;
	final Node[] nodes;
	
	//constructor
	public Graph(int nbnodes, int nestnode, double[][] weights){
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
	
	//methods
	public double getTotalW() {
		return totalW;
	}
	
	public int getNbNodes() {
		return nbnodes;
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
	
	public Node[] getNodes() {
		return nodes;
	}
	
	//prints graph
	public void print() {
		for(int i = 0; i<nbnodes;i++) {
			nodes[i].print();
		}
	}
}
