package aco_sim;

import pec.*;

public class AcoSimulator {
	//attributes
	Ant[] ants;
	PEC pec;
	double[] par;
	
	//constructor
	public AcoSimulator(int n, int n1, double[][] aij, double alpha, double beta, double delta,
						double eta, double rho, double gamma, int miu, double tau) {
		pec = new PEC();
		ants = new Ant[miu];
		AcoGraph g = new AcoGraph(n, n1, aij);
		for(int i=0; i<miu; i++) 
			ants[i] = new Ant(g, gamma);
		par = new double[]{alpha, beta, delta, eta, rho, gamma, tau};
		this.run();
	}
	
	//run simulation until final instant is reached
	private void run() {
		//initialize events
		initEvents();
		//get initial event
		Event currentEvent = pec.nextEvPEC();
		double currentTime = currentEvent.getTimestamp();
		//run while current time lower than tau
		while(currentTime < this.par[6]) {
			currentEvent.simulate(pec);
			currentEvent = pec.nextEvPEC();
			currentTime += currentEvent.getTimestamp();
		}
	}
	
	//initialize events
	void initEvents() {
		/*init moves*/;
	}
	
	//other methods
	public void printG() {
		System.out.println(this.ants[0].G.toString());
	}
}
