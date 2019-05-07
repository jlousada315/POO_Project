package eventHandler;

import pec.*;
import xml_utils.Var;
import aco_tools.Prob;
import graph.Graph;

public class Evap extends Event {

	//constructor
	public Evap(Object obj, double timestamp) {
		super(obj, timestamp);
	}

	//simulate this event
	@Override
	public void simulate(PEC pec, Graph G, Var v) {
		//executes current event
		if(G.evapFromEdge((int[])obj))
			//creates next event
			pec.addEvPEC(newEvap(v.getEta()));
	}
	
	//creates next event
	private Evap newEvap(double eta) {
		return new Evap(obj, Prob.expRand(eta));
	}
}