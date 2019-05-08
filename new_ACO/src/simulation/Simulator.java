    
package simulation;

import xml_utils.Var;
import pec.Event;

import eventHandler.*;
import graph.Graph;
import pec.*;

public class Simulator implements ISimulator {
	//fields
	Var v;
	PEC pec;
	Graph G;
	Ant[] ants;
	int[] counter; // totalEvents, nbMoves, obsNum
	
	//constructor
	public Simulator(Var var) {
		this.v = var;
		pec = new PEC();
		G = new Graph(v);
		ants = new Ant[var.getAntcolsize()];
		for(int i=0; i<ants.length; i++)
			ants[i] = new Ant(v.getNestnode());
		counter = new int[3];
		this.run();
	}
	
	@Override
	public void run() {
		//initialize events
		this.initEvents();
		//get initial event
		Event currentEvent = pec.nextEvPEC();
		double currentTime = currentEvent.getTimestamp();
		//run while current time lower than finalinst
		while(currentTime < v.getFinalinst() || pec.nextEvPEC()!=null) {
			if(currentTime >= (counter[2]*(v.getFinalinst()/20))) {
				print(currentTime);
			}
			currentEvent.simulate(pec, G, v);
			currentEvent = pec.nextEvPEC();
			if(currentEvent != null)
				currentTime = currentEvent.getTimestamp();
			else
				break;
			if(currentEvent instanceof Move) {
				counter[1]++;
			}
			counter[0]++;
		}
		System.out.println("Time limit reached: t = " + v.getFinalinst());
		System.out.println("Best Hamiltonian: " + G.getBestHamiltonian());
	}

	@Override
	public void initEvents() {
		//init moves
		for(int i=0; i<ants.length; i++) {
			Move aux = new Move(ants[i], 0);
			aux.simulate(pec, G, v);
		}
		//init counters
		counter[0] = v.getAntcolsize();
		counter[1] = v.getAntcolsize();
		counter[2] = 1;
	}

	@Override
	public void print(double currentTime) {
		//prints simulation information
		System.out.println("Observation number: " + counter[2]);				
		System.out.println("		Present instant: " + currentTime);
		System.out.println("		Number of move events: " + counter[1]); 
		System.out.println("		Number of evaporation events: " + (counter[0]-counter[1]));
		System.out.println("		Hamiltonian cycle: " + G.getBestHamiltonian());
		++counter[2];
	}
}