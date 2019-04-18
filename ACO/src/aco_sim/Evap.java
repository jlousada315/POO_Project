package aco_sim;

public class Evap extends Event {

	private double eta;
	private double rho;
	
	public Evap(double Eta, double Rho) {
		// TODO Auto-generated constructor stub
		eta = Eta;
		rho = Rho;
	}

	//add event to PEC with new timestamp
		public void simulate(PEC p,Event ev) {
			p.addEvPEC(ev);
		}
}
