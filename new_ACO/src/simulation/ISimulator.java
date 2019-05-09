package simulation;

/**
 * Interface for the simulation.
 *
 */
public interface ISimulator {
	/**
	 * Runs the simulation
	 */
	void run();
	
	/**
	 * Initialize move events.
	 */
	void initEvents();

	/**
	 * print method
	 * @param time current time.
	 */
	void print(double time);
}