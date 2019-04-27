package aco_sim;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import graph.*;

public class AcoNode implements Node {
	//attributes
	protected final int nodeidx;
	protected final ArrayList<AcoEdge> edges;
	
	//constructor
	AcoNode(int nodeidx){
		this.nodeidx = nodeidx;
		edges = new ArrayList<AcoEdge>();
	}
	
	//rewrite setEdge
	void setEdge(AcoNode node2, double weight) {
		edges.add(new AcoEdge(this, node2, weight));
	}
	
	//get node index
	public int getIdx() {
		return nodeidx;
	}

	//get edge from node2	
	public Edge getEdge(AcoNode node2) {
		for(int i=0; i<edges.size(); i++)
			if(edges.get(i).node2.equals(node2))
				return edges.get(i);
		throw new NoSuchElementException("Edge does not exists.");
	}

	@Override
	public int getEdgesSize() {
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
		AcoNode other = (AcoNode) obj;
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
