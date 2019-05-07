package simulation;

import graph.Graph;

import eventHandler.Ant;
import eventHandler.Move;
import pec.*;
import xml_utils.Var;

public class Simulator {
	//attributes
	Ant[] ants;
	PEC pec;
	private Event currentEvent;
	
	//constructor
	public Simulator(Var v , Graph G, Ant[] ants) {
		pec = new PEC();
		this.ants = ants;
		
		//this.run();
	}
	
	//run simulation until final instant is reached
	public void run(Var v) {
		int obs_nb = 1;
		//initialize events
		init();
		//get initial event
		currentEvent = pec.nextEvPEC();
		double currentTime = currentEvent.getTimestamp();
		//run while current time lower than tau
		int nbmoves = v.getAntcolsize();
		int totalEvents = v.getAntcolsize();;
		while(currentTime < v.getFinalinst()) {
			//Prints information
			if(currentTime >= (obs_nb*(v.getFinalinst()/20))) {
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
		/*		if(((Ant)currentEvent.obj).hFlag) {
					initEvap();
				}*/
			}
			
			currentEvent = pec.nextEvPEC();
			currentTime += currentEvent.getTimestamp();
			
			totalEvents++;		
			
		}
		System.out.println("Time limit reached: t = " + v.getFinalinst());
	}
	
	void init() {
		;
	}
	
	/*
	 
	 
	//initialize events
	void initEvents() {
		//init moves and evaps
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
	*/
	//other methods
	public void printG(Graph G) {
		System.out.println(G.toString());
	}
}