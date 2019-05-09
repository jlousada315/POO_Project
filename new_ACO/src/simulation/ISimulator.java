package simulation;

public interface ISimulator {
	//runs the simulation
	void run();
	
	//initialize events
	void initEvents();

	//print method
	void print(double time);
}