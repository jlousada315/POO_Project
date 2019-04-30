package aco_sim;

import pec.*;

public class AcoSimulator {
	//attributes
	Ant[] ants;
	PEC pec;
	double[] par;
	Move[] init;
	
	
	//constructor
	public AcoSimulator(int n, int n1, double[][] aij, double alpha, double beta, double delta,
						double eta, double rho, double gamma, int miu, double tau) {
		pec = new PEC();
		ants = new Ant[miu];
		init = new Move[miu];
		AcoGraph g = new AcoGraph(n, n1, aij);
		for(int i=0; i<miu; i++) 
			ants[i] = new Ant(g, gamma);
		par = new double[]{alpha, beta, delta, eta, rho, gamma, tau};
		//this.run();
	}
	
	//run simulation until final instant is reached
	public void run() {
		int obs_nb = 1;
		//initialize events
		initEvents();
		//get initial event
		Event currentEvent = pec.nextEvPEC();
		double currentTime = currentEvent.getTimestamp();
		//run while current time lower than tau
		while(currentTime < this.par[6]) {
			//Prints information
			if((int)currentTime > (obs_nb*(par[6]/20))) {
				System.out.println("Observation number: " + (obs_nb));				
				System.out.println("Present instant: " + currentTime);
				System.out.println("Number of move events: " );  //implementation missing
				System.out.println("Number of evaporation events: " ); //implementation missing
				System.out.println("Hamiltonian cycle: ");		 //implementation missing
				++obs_nb;
			}
			currentEvent.simulate(pec);
			currentEvent = pec.nextEvPEC();
			currentTime += currentEvent.getTimestamp();
			
		}
		System.out.println("Time limit reached: t = " + par[6]);
	}
	
	//initialize events
	void initEvents() {
		/*init moves*/
		for(int i = 0; i < ants.length ; i++) {
			init[i] = new Move(ants[i], 0 ,par[0], par[1], par[2], par[5]);		
			pec.addEvPEC(init[i]);
			init[i].simulate(pec);
		}	
	}
	
	//other methods
	public void printG() {
		System.out.println(this.ants[0].G.toString());
	}
}