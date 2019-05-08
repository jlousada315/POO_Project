package eventHandler;

import pec.*;
import xml_utils.Var;
import aco_tools.Prob;
import graph.Graph;

public class Evap extends Event{

	//constructor
	public Evap(Object obj, double timestamp) {
		super(obj, timestamp);
	}

	//simulate this event
	@Override
	public void simulate(PEC pec, Graph G, Var v) {
		double time = Prob.expRand(v.getEta());
		//executes current event
		if(G.evapFromEdge((int[])obj) && getTimestamp()+ time < v.getFinalinst())
			pec.addEvPEC(newEvap(time));
	}
	
	//creates next event
	private Evap newEvap(double time) {
		return new Evap(obj, getTimestamp()+time);
	}
}