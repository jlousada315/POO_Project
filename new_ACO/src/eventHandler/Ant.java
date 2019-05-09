package eventHandler;

import java.util.*;

import graph.*;
import pec.PEC;
import xml_utils.Var;

/**
 * Used to represent an ant in an ant colony optimization
 * algorithm. Stores the taken path and implements 
 * the methods necessary to handle it, 
 * also offers a method to check if an 
 * Hamiltonian cycle has been found.
 */
public class Ant {
	
	
	//attributes
	protected LinkedList<Integer> path;
	
	/**
	 * Constructor for ant
	 * 
	 * @param nest Nest node.
	 */
	public Ant(int nest) {
		path = new LinkedList<Integer>();
		path.add(nest);
	}
	
	/**
	 * Returns stored path in this class.
	 * 
	 * @return path 
	 */
	public LinkedList<Integer> getPath() {
		return path;
	}
	
	/**
	 * Returns last visited node.
	 * 
	 * @return last visited node
	 */
	public int getLast() {
		return path.getLast();
	}

	/**
	 * Resets path to Nest Node to find 
	 * another Hamiltonian cycle.
	 */
	public void resetPath() {
		int nest = path.getFirst();
		path.clear();
		path.add(nest);
	}
	
	//prints path 
	public void printPath() {
		for(int i=0 ;i < path.size(); ++i ) {
			System.out.println(path.get(i).toString());
		}
	}
	
	@Override
	public String toString() {
		int arr[] = new int[path.size()];
		for(int i=0 ;i < path.size(); ++i ) {
			arr[i] = path.get(i);
		}
		return Arrays.toString(arr);
	}
	
	
	/**
	 * Checks if path contains a Hamiltonian cycle
	 * and returns true if it is, false otherwise.
	 * @param X Base node index.
	 * @param nbnodes Number of nodes.
	 * 
	 * @return True or False 
 	 * 
	 */
		boolean isHamiltonian(int X,int nbnodes) {
			if(path.size()== nbnodes && X == path.getFirst()){return true;}
			else {return false;}
		}

	
	/**
	 * Checks if there are duplicated nodes in path
	 * and returns true if there are, false otherwise.
	 * 
	 * @return True or False.
	 */
	boolean hasDuplicate() {
		for(int i = 0; i < path.size() ; ++i) {
			for(int j = 0; j < path.size() ; ++j) {
				if(path.get(i) == path.get(j) && i!=j ) {
					return true;
				}
			}
		}
		return false;
	}
			
	/**
	 * Updates Ant's path according to rules.
	 * 
	 * @param next_nodeidx Next node index.
	 * @param G Graph.
	 * @param v Element from class var.
	 * @param pec PEC.
	 * @param timestamp Current Simulation time.
	 */
	public void updatePath(int next_nodeidx, IGraph G, Var v, PEC pec, double timestamp) {
		if(hasDuplicate()) {
			int i=path.size()-1;
			while(path.getLast().equals(path.get(i-1)) != true) {
				--i;
			}
			for(int j = path.size()-1 ; j >= i;--j) {
				path.remove(j);
			}
			next_nodeidx = G.nextNode(this);
			updatePath(next_nodeidx,G,v,pec,timestamp);
		}else if(isHamiltonian(next_nodeidx, G.getSize())) {
			G.updateHamiltonian(path);
			G.initEvap(path, pec, timestamp);
			G.updatePheromones(path);
			//System.out.println("Hamiltonian Cycle Found with path = " + path);
			resetPath();
		}else{ 
			path.add(next_nodeidx);
		} 
	}
}