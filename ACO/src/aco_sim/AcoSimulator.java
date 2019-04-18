package aco_sim;

public class AcoSimulator {
	//attributes
	Graph G;
	Ant[] ants;
	PEC pec;
	double[] par;
	
	//constructor
	public AcoSimulator(int n, int n1, double[][] aij, double alpha, double beta, double delta,
						double eta, double rho, double gamma, int miu, double tau) {
		G = new Graph(n, n1, aij);
		ants = new Ant[miu];
		pec = new PEC();
		par = new double[]{alpha, beta, delta, eta, rho, gamma, tau};
		this.run();
	}
	
	private void run() {
		;
	}
	
}