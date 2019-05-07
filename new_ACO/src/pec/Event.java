package pec;

import graph.Graph;
import xml_utils.Var;

public abstract class Event {
	//attributes
	double timestamp=0;
	public Object obj;
	
	//constructor
	public Event(Object obj, double timestamp){
		this.obj = obj;
		this.timestamp = timestamp;
	}
	
	//abstract simulate
	abstract public void simulate(PEC pec, Graph G, Var v);
	
	//returns timestamp
	public double getTimestamp() {
		return timestamp;
	}
	
	public String toString() {
		return (obj.toString() + "time :" + timestamp);
	}
}
