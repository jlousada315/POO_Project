package simulation;

import pec.*;

public class Simulator {
	//attributes
	Ant[] ants;
	PEC pec;
	double[] par;
	Move[] initM;
	String[] GlobalHamiltonian;
	private Event currentEvent;
	
	//constructor
	public Simulator(int n, int n1, double[][] aij, double alpha, double beta, double delta,
						double eta, double rho, double gamma, int miu, double tau) {
		pec = new PEC();
		ants = new Ant[miu];
		initM = new Move[miu];
		Graph g = new Graph(n, n1, aij);
		for(int i=0; i<miu; i++) 
			ants[i] = new Ant(g, gamma);
		par = new double[]{alpha, beta, delta, eta, rho, gamma, tau, miu};
		//this.run();
	}
	
	//run simulation until final instant is reached
	public void run() {
		int obs_nb = 1;
		//initialize events
		initEvents();
		//get initial event
		currentEvent = pec.nextEvPEC();
		double currentTime = currentEvent.getTimestamp();
		//run while current time lower than tau
		int nbmoves = (int)par[7];
		int totalEvents = (int)par[7];
		while(currentTime < this.par[6]) {
			//Prints information
			if(currentTime >= (obs_nb*(par[6]/20))) {
				System.out.println("Observation number: " + (obs_nb));				
				System.out.println("		Present instant: " + currentTime);
				System.out.println("		Number of move events: " + nbmoves);  //implementation missing
				System.out.println("		Number of evaporation events: " + (totalEvents-nbmoves) ); //implementation missing
				System.out.println("		Hamiltonian cycle: ");		 //implementation missing
				++obs_nb;
			}

			currentEvent.simulate(pec);
			if(currentEvent instanceof Move) {
				nbmoves++;
				if(((Ant)currentEvent.obj).hFlag) {
					initEvap();
				}
			}
			
			currentEvent = pec.nextEvPEC();
			currentTime += currentEvent.getTimestamp();
			
			totalEvents++;		
			
		}
		System.out.println("Time limit reached: t = " + par[6]);
	}
	
	//initialize events
	void initEvents() {
		/*init moves and evaps*/
		for(int i = 0; i < ants.length ; i++) {
			initM[i] = new Move(ants[i], 0 ,par[0], par[1], par[2], par[5]);		
			pec.addEvPEC(initM[i]);	
			initM[i].simulate(pec);
		}	
	}
	
	void initEvap() {
		
		Edge aux;
		for(int i = 0; i < ((Ant)currentEvent.obj).path.size()-1 ; i++) { //path is empty.
				aux = ((Ant)currentEvent.obj).path.get(i).getEdge(((Ant)currentEvent.obj).path.get(i+1));
				pec.addEvPEC(new Evap(aux, par[3], par[4]));
		}
	}
	
	//other methods
	public void printG() {
		System.out.println(this.ants[0].G.toString());
	}
}