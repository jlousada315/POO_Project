package projectoPOO;
import java.util.*;

public class Node {
	//attributes
	int nodeidx;
	ArrayList<Edge> edges;
	
	//constructor
	Node(int nodeidx){
		//initialize attributes
		this.nodeidx = nodeidx;
		edges = new ArrayList<Edge>();
	}
	void setEdge(Node node2, double weight) {
		edges.add(new Edge(this, node2, weight));
	}
}
