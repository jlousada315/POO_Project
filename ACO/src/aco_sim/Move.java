package aco_sim;

public class Move implements Event{

	final double alpha;
	final double beta;
	final double delta;
	double timestamp = 0;
	
	
	public Move(double Alpha, double Beta, double Delta) {
		// TODO Auto-generated constructor stub
		alpha = Alpha;
		beta= Beta;
		delta = Delta;
	}

	public void Simulate() {
		//add event to PEC with new timestamp
		
	}
	
	public void Settimestamp(double m) {
		timestamp = m;
	}
	
	public double Gettimestamp() {
		return timestamp;
	}

}
