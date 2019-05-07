package eventHandler;

import java.util.*;

import graph.*;
import xml_utils.Var;

public class Ant {
	//attributes
	protected LinkedList<Integer> path;
	
	//constructor
	public Ant(int nest) {
		path = new LinkedList<Integer>();
		path.add(nest);
	}
	
	public LinkedList<Integer> getPath() {
		return path;
	}
	
	public int getLast() {
		return path.getLast();
	}

	//resets path to Node to find another Hamiltonian cycle
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
	
	//toString method
	@Override
	public String toString() {
		int arr[] = new int[path.size()];
		for(int i=0 ;i < path.size(); ++i ) {
			arr[i] = path.get(i);
		}
		return Arrays.toString(arr);
	}
	
	
	//checks if path contains a Hamiltonian cycle
		boolean isHamiltonian(int X,int nbnodes) {
			if(path.size()== nbnodes && X == path.getFirst()){return true;}
			else {return false;}
		}

	
	//checks if there are duplicated nodes in path
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
			
	//update's Ant's path according to rules.
	public void updatePath(int next_nodeidx, IGraph G,Var v) {
		if(hasDuplicate()) {
			int i=path.size()-1;
			while(path.getLast().equals(path.get(i-1)) != true) {
				--i;
			}
			for(int j = path.size()-1 ; j >= i;--j) {
				path.remove(j);
			}
		}else if(isHamiltonian(next_nodeidx,G.getSize())) {
			G.updatePheromones(v, this);
			System.out.println("Hamiltonian Cycle Found with path = " + "m");
			resetPath();
		}else{
			path.add(next_nodeidx);
		} 
	}
	
	
}