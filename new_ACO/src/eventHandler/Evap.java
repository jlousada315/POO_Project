package eventHandler;

import pec.*;
import aco_tools.Prob;

public class Evap extends Event{

	
	//constructor
	public Evap(Object obj, double timestamp) {
		super(obj, timestamp);
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