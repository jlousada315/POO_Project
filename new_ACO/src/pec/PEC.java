package pec;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  Used to implement an Priority Event Container.
 */
public class PEC {
	//attributes
	PriorityQueue<Event> pqEv;
	
	/**
	 * Compares two Events by their timestamp.
	 */
	private static class EventComparator implements Comparator<Event> {
		@Override
		public int compare(Event l, Event r) {
			return (int)(l.getTimestamp() - r.getTimestamp());
		}
	} 
	
	/**
	 * Constructor for PEC
	 */
	public PEC() {
		EventComparator EvComp = new EventComparator();
		pqEv = new PriorityQueue<Event>(EvComp);
	}

	/**
	 * adds the received event to the PEC.
	 * 
	 * @param ev Event to be added to the PEC.
	 */
	public void addEvPEC(Event ev) {
		if(ev != null)
			pqEv.add(ev);
	}

	/**
	 * Removes and retrieves the first event from the PEC.
	 * 
	 * @return Event from PEC.
	 */
	public Event nextEvPEC() {
		return pqEv.poll();
	}
}