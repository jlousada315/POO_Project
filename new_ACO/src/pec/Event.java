package pec;

import graph.Graph;
import xml_utils.Var;

/**
 * Used to represent an Event in the PEC. Stores the 
 * event timestamp, and an element from class Object.
 */
public abstract class Event {
	//attributes
	double timestamp=0;
	public Object obj;
	
	/**
	 * Constructor for class Event.
	 * @param obj Object
	 * @param timestamp Current time.
	 */
	public Event(Object obj, double timestamp){
		this.obj = obj;
		this.timestamp = timestamp;
	}
	
	/**
	 * Schedules a PEC event.
	 * @param pec PEC.
	 * @param G Graph.
	 * @param v Var.
	 */
	abstract public void simulate(PEC pec, Graph G, Var v);
	
	/**
	 * Returns stored timestamp.
	 * @return timestamp Stored timestamp.
	 */
	public double getTimestamp() {
		return timestamp;
	}
	
	@Override
	public String toString() {
		return (obj.toString() + "time :" + timestamp);
	}
}