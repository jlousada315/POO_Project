package graph;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Node {
	//attributes
	protected final int nodeidx;
	protected ArrayList<Edge> edges;
	
	//constructor
	Node(int nodeidx){
		this.nodeidx = nodeidx;
		edges = new ArrayList<Edge>();
	}
	
	//rewrite setEdge
	void setEdge(Node node2, double weight) {
		edges.add(new Edge(this, node2, weight));
	}
	
	//get edge from node2	
	Edge getEdge(Node node2) {
		for(int i=0; i<edges.size(); i++)
			if(edges.get(i).node2.equals(node2))
				return edges.get(i);
		throw new NoSuchElementException("Edge does not exists.");
	}

	int getEdgesSize() {
		return edges.size();
	}
	
	//generated hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nodeidx;
		return result;
	}

	//generated equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeidx != other.nodeidx)
			return false;
		return true;
	}

	//generated toString
	@Override
	public String toString() {
		String str = "Node [" + nodeidx +"]";
		for(int i=0; i<edges.size(); i++) {
			str+= edges.get(i).toString()+"\n";
		}
		return str;		
	}
}