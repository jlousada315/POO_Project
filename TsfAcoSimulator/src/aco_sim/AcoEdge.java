package aco_sim;

import graph.*;

public class AcoEdge implements Edge {
	//attributes
	final AcoNode node1;
	final AcoNode node2;
	final double weight;
	double pheromone=0;
	
	//constructor
	AcoEdge(AcoNode node1, AcoNode node2, double weight){
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight; 
	}
	
	//get node2
	@Override
	public AcoNode getNode2() {
		return node2;
	}
	
	//get weight
	public double getWeight() {
		return weight;
	}
	
	//generated hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
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
		AcoEdge other = (AcoEdge) obj;
		if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if (!node1.equals(other.node1))
			return false;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (!node2.equals(other.node2))
			return false;
		return true;
	}

	//generated toString
	@Override
	public String toString() {
		return "Edge [("+node1+","+node2+"),w="+weight+",p="+pheromone+"]";
	}
}