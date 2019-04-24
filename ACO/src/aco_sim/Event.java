package aco_sim;

public abstract class Event {
	//attributes
	public double timestamp=0;
	Object obj;
	
	//constructor
	Event(Object obj, double timestamp){
		this.obj = obj;
		this.timestamp = timestamp;
	}
	
	//abstract methods
	abstract void simulate(PEC pec);
}