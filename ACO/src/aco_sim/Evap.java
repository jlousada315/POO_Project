package aco_sim;

public class Evap implements Event {

	private final double eta;
	private final double rho;
	double timestamp = 0;
	
	
	public Evap(double Eta, double Rho) {
		// TODO Auto-generated constructor stub
		eta = Eta;
		rho = Rho;
	}

	public void Simulate() {
		
	}
	
	public void Settimestamp(double m) {
		timestamp = m;
	}
	
	public double Gettimestamp() {
		return timestamp;
	}

}
