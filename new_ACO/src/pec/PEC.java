package pec;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PEC {
	//attributes
	PriorityQueue<Event> pqEv;
	
	//Compares two Events by their timestamp
	private static class EventComparator implements Comparator<Event> {
		public int compare(Event l, Event r) {
			return (int)(l.getTimestamp() - r.getTimestamp());
		}
	} 
	
	//constructor
	public PEC() {
		EventComparator EvComp = new EventComparator();
		pqEv = new PriorityQueue<Event>(EvComp);
	}

	//adds the received event to the PEC
	public void addEvPEC(Event ev) {
		if(ev != null)
			pqEv.add(ev);
	}

	//removes and retrieves the first event from the PEC
	public Event nextEvPEC() {
		return pqEv.poll();
	}
}