package pec;

public abstract class Event {
	//attributes
	double timestamp=0;
	Object obj;
	
	//constructor
	public Event(Object obj, double timestamp){
		this.obj = obj;
		this.timestamp = timestamp;
	}
	
	//abstract simulate
	abstract public void simulate(PEC pec);
	
	//returns timestamp
	public double getTimestamp() {
		return timestamp;
	}
}
