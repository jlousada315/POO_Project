package eventHandler;

import pec.*;
import xml_utils.Var;
import aco_tools.Prob;
import graph.IGraph;


/**
 * Used to create and manage an Evap even in the PEC.
 */
public class Evap extends Event{

	/**
	 * Constructor of Evap class.
	 * @param obj Edge.
	 * @param timestamp Current time.
	 */
	public Evap(Object obj, double timestamp) {
		super(obj, timestamp);
	}

	@Override
	public void simulate(PEC pec, IGraph G, Var v) {
		double time = Prob.expRand(v.getEta());
		//executes current event
		if(G.evapFromEdge((int[])obj) && timestamp + time < v.getFinalinst())
			pec.addEvPEC(newEvap(time));
	}
	
	/**
	 * Creates next evap event
	 * @param time Time until next evaporation.
	 * @return Evap.
	 */
	private Evap newEvap(double time) {
		return new Evap(obj, timestamp +time);
	}
}