package simulation;

/**
 * Interface for the simulation.
 *
 */
public interface ISimulator {
	/**
	 * Runs the simulation
	 */
	public void run();
	
	/**
	 * Initialize move events.
	 */
	public void initEvents();

	/**
	 * print method
	 * @param time current time.
	 */
	public void print(double time);
}