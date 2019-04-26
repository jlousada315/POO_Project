package aco_sim;

import graph.*;

public class AcoEdge extends Edge {
	//attributes
	final double weight;
	double pheromone=0;
	
	//constructor
	AcoEdge(Node node1, Node node2, double weight){
		super(node1,node2);
		this.weight = weight; 
	}
	
	//get weight
	public double getWeight() {
		return weight;
	}
	
	//get pheromone
	public double getPheromone() {
		return pheromone;
	}
	
	//set pheromone value
	public void setPheromone(double value) {
		if(value == 0) 
			pheromone = 0;
		else
			pheromone += value;
	}
	
	//generated toString
	@Override
	public String toString() {
		return super.toString()+" - [w="+weight+",p="+pheromone+"]";
	}
}
