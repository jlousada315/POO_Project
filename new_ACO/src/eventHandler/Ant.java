package eventHandler;

import java.util.*;

import graph.*;
import pec.PEC;
import xml_utils.Var;

public class Ant implements IAnt{
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
	private void resetPath() {
		int nest = path.getFirst();
		path.clear();
		path.add(nest);
	}
	
	//checks if path contains a Hamiltonian cycle
	private boolean isHamiltonian(int X,int nbnodes) {
		if(path.size()== nbnodes && X == path.getFirst()){return true;}
		else {return false;}
	}

	//checks if there are duplicated nodes in path
	private boolean hasDuplicate() {
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
			resetPath();
		}else{ 
			path.add(next_nodeidx);
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
}