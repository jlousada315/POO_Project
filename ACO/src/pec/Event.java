package pec;

public abstract class Event {
	//attributes
	public double timestamp=0;
	Object obj;
	
	//constructor
	public Event(Object obj, double timestamp){
		this.obj = obj;
		this.timestamp = timestamp;
	}
	
	//abstract methods
	abstract public void simulate(PEC pec);
}
