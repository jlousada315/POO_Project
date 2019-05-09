package eventHandler;

import java.util.LinkedList;

import graph.IGraph;
import pec.PEC;
import xml_utils.Var;

/**
 *  Interface that contains methods for path handling
 *
 */
public interface ITraveller {
	
	/**
	 * Returns stored path in this class.
	 * 
	 * @return path 
	 */
	public LinkedList<Integer> getPath();
	
	/**
	 * Returns last visited node.
	 * 
	 * @return last visited node
	 */
	public int getLast();
	
	/**
	 * Updates Ant's path according to rules.
	 * 
	 * @param next_nodeidx Next node index.
	 * @param G IGraph.
	 * @param v Element from class var.
	 * @param pec PEC.
	 * @param timestamp Current Simulation time.
	 */
	public void updatePath(int next_nodeidx, IGraph G, Var v, PEC pec, double timestamp);
}