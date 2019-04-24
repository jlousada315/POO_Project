package aco_sim;
import graph.*;
import pec.*;

public class AcoSimulator {
	//attributes
	Graph G;
	Ant[] ants;
	PEC pec;
	double[] par;
	
	//constructor
	public AcoSimulator(int n, int n1, double[][] aij, double alpha, double beta, double delta,
						double eta, double rho, double gamma, int miu, double tau) {
		// TODO Auto-generated constructor stub
		G = new Graph(n, n1, aij);
		pec = new PEC();
		ants = new Ant[miu];
		for(int i=0; i<miu; i++) 
			ants[i] = new Ant(G, gamma);
		par = new double[]{alpha, beta, delta, eta, rho, gamma, tau};
		this.run();
	}
	
	//run simulation until final instant is reached
	private void run() {
		//initialize events
		initEvents();
		//get initial event
		Event currentEvent = pec.nextEvPEC();
		double currentTime = currentEvent.timestamp;
		//run while current time lower than tau
		while(currentTime < this.par[6]) {
			currentEvent.simulate(pec);
			currentEvent = pec.nextEvPEC();
			currentTime += currentEvent.timestamp;
		}
	}
	
	//initialize events
	void initEvents() {
		/*init moves*/;
	}
	
	//other methods
	public void printG() {
		this.G.print();
	}
}