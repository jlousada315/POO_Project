package aco_sim;

public class Edge {
	//attributes
	Node node1;
	Node node2;
	double weight;
	double pheromone=0;
	
	//constructor
	Edge(Node node1, Node node2, double weight){
		//initialize attributes
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight; 
	}
	
	//sets and gets
	void setWeight(double value) {
		weight = value;
	}
	
	double getWeight() {
		return weight;
	}
	
	void setPheromone(double value) {
		pheromone = value;
	}
	
	double getPheromone() {
		return pheromone;
	}
	
	Node getNode2() {
		return node2;
	}
	
	//methods
	
	//other methods
	void print() {
		System.out.print(" Node 1: " + node1.nodeidx);
		System.out.print(" Node 2: " + node2.nodeidx);
		System.out.print(" w: " + weight);
		System.out.print(" p: " + pheromone);
		System.out.println();
	}
}