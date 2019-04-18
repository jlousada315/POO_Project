package aco_sim;

public class Evap extends Event{
	
	private double eta;
	private double rho;
	
	public Evap(double eta, double rho) {
		// TODO Auto-generated constructor stub
		this.eta = eta;
		this.rho = rho;
	}

	public void simulate() {
		
	}
	
	public void setTimeStamp(double m) {
		timestamp = m;
	}
	
	public double getTimeStamp() {
		return timestamp;
	}
}