package aco_sim;

public class Move extends Event{

	final double alpha;
	final double beta;
	final double delta;
	final double plevel;
	
	public Move(double Alpha, double Beta, double Delta,double Plevel) {
		// TODO Auto-generated constructor stub
		alpha = Alpha;
		beta= Beta;
		delta = Delta;
		plevel = Plevel;
	}

	public void simulate() {
		//add event to PEC with new time stamp
		
	}
	
	public void setTimeStamp(double m) {
		timestamp = m;
	}
	
	public double getTimeStamp() {
		return timestamp;
	}

}