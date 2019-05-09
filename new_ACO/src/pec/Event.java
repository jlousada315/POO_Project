package pec;

import graph.IGraph;
import xml_utils.Var;

public abstract class Event {
	//attributes
	protected double timestamp=0;
	protected Object obj;
	
	//constructor
	public Event(Object obj, double timestamp){
		this.obj = obj;
		this.timestamp = timestamp;
	}
	
	//abstract simulate
	abstract public void simulate(PEC pec, IGraph G, Var v);
	
	//returns timestamp
	public double getTimestamp() {
		return timestamp;
	}
	
	@Override
	public String toString() {
		return (obj.toString() + "time :" + timestamp);
	}
}