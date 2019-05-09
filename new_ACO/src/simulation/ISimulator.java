package simulation;

public interface ISimulator {
	//runs the simulation
	public void run();
	
	//initialize events
	public void initEvents();

	//print method
	public void print(double time);
}