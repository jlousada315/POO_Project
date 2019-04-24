package aco_sim;

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
	void simulate(PEC pec) {
		//executes current event
		evapFromEdge((Edge)obj);
		//creates next event
		pec.addEvPEC(newEvap());
	}
	
	//evaporates pheromones by rho
	void evapFromEdge(Edge edge) {
		if(edge.pheromone>rho)
			edge.pheromone -= rho;
		else
			edge.pheromone = 0;
	}
	
	//creates next event
	Evap newEvap() {
		return new Evap(obj, eta, rho);
	}
}