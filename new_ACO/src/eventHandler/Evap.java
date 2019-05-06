package eventHandler;

import pec.*;
import aco_tools.Prob;

public class Evap extends Event{
	//attributes
	final double eta;
	final double rho;
	
	//constructor
	public Evap(Object obj, double eta, double rho) {
		super(obj, Prob.expRand(eta));
		this.eta = eta;
		this.rho = rho;
	}

	//simulate this event
	@Override
	public void simulate(PEC pec) {
		//executes current event
		if(evapFromEdge())
			//creates next event
			pec.addEvPEC(newEvap());
	}
	
	//evaporates pheromones by rho
	private boolean evapFromEdge() {
		if(((Edge)obj).pheromone>rho) {
			((Edge)obj).pheromone -= rho;
			return true;
		}
		((Edge)obj).pheromone = 0;
		return false;
	}
	
	//creates next event
	private Evap newEvap() {
		return new Evap(obj, eta, rho);
	}
}