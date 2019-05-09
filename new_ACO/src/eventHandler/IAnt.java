package eventHandler;

import java.util.LinkedList;

import graph.IGraph;
import pec.PEC;
import xml_utils.Var;

public interface IAnt {
	
	public LinkedList<Integer> getPath();
	
	public int getLast();
	
	public void updatePath(int next_nodeidx, IGraph G, Var v, PEC pec, double timestamp);
}
