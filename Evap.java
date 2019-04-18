package aco_sim;

public class Evap extends Event{
	//attributes
	private double gamma;
	private double eta;
	private double rho;
	
	//constructor
	public Evap(double eta, double rho, double gamma) {
		// TODO Auto-generated constructor stub
		this.eta = eta;
		this.rho = rho;
		this.gamma = gamma;
	}

	//methods
	public void simulate(PEC p, Event ev) {
		//add event to PEC with new time stamp
		p.addEvPEC(ev);
	}
	
	public void updatePheromones(Ant A) {
		//if cycle is Hamiltonian, increase level of pheromones on the path
		if(A.duplicate(A.path.get(0))==2) {
			double pathW = 0;
			for(int i=0; i<A.path.size()-1; i++) {
				pathW += A.path.get(i).getEdgeWeight(A.path.get(i+1));
			}
			double updateValue = gamma*A.G.totalW/pathW;
			for(int i=0; i<A.path.size()-1; i++) {
				A.path.get(i).getEdge(A.path.get(i+1)).pheromone += updateValue;
			}
		} 
	}
	
	public void evapFromEdge(Edge edge) {
		edge.pheromone -= rho;
		// adicionar time stamp para novo evento?
	}
}

