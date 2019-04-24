package pec;
import aco_sim.Prob;
import graph.Edge;

public class Evap extends Event {
	//attributes
	final double eta;
	final double rho;
	
	//constructor
	public Evap(Object obj, double eta, double rho) {
		super(obj, Prob.expRand(eta));
		this.obj = obj;
		this.eta = eta;
		this.rho = rho;
	}
	
	@Override
	public void simulate(PEC pec) {
		//executes current event
		if(evapFromEdge((Edge)obj))
			//creates next event
			pec.addEvPEC(newEvap());
	}
	
	//evaporates pheromones by rho
	boolean evapFromEdge(Edge edge) {
		if(edge.getPheromone()>rho) {
			edge.setPheromone(-rho);
			return true;
		}
		edge.setPheromone(0);
		return false;
	}
	
	//creates next event
	Evap newEvap() {
		return new Evap(obj, eta, rho);
	}
}