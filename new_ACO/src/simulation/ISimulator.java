package simulation;

import java.util.LinkedList;

public interface ISimulator {
	//runs the simulation
	void run();
	
	//initialize events
	void initEvents();

	//print method
	void print(double time);
}