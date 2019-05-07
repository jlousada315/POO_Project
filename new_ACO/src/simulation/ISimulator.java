package simulation;

public interface ISimulator {
	//runs the simulation
	void run();
	
	//initialize events
	void initEvents();

	//save best hamiltonian
	void bestHamiltonian(int[] path, double pathWeight);
	
	//print method
	void print(double time);
}
