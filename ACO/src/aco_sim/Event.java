package aco_sim;

public abstract class Event {
	
	public double timestamp=0;
	
	abstract void simulate();
	
	void setTimeStamp(double m) {
		timestamp = m ;
	}
	
	double getTimeStamp() {
		return timestamp;
	}
	
}
