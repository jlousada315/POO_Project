package graph;

import xml_utils.Var;
import eventHandler.Ant;

public interface IGraph {
	
	//returns nest node
	Node getNest();
	int getSize();
	Node getNode(int nodeidx);
	
	//return total weigth of path
	//double pathWeigth(int[] path);
	
	//calculates prob of nextnodes
	Double[] calculateProb(Var v, Ant A);
	
	//returns index of next node.
	int nextNode(Var v, Ant A);
	
	//updates Pheromone level.
	void updatePheromones(Var v,Ant A);

}