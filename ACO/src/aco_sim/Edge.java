package projectoPOO;

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
	
}
