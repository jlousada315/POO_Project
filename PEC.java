package aco_sim;

import java.util.LinkedList;
import aco_sim.Event;

public class PEC {
	
	/*Fields*/
	LinkedList<Event> E;
	
	//Constructor create empty list of events	
	public PEC() {
		// TODO Auto-generated constructor stub
		E =  new LinkedList<Event>();
	}
	
	//adds the received event to the PEC
	public void addEvPEC(Event ev) {
		if(ev != null)
			E.add(ev);
	}
	
	//removes the first event from the PEC and returns it
	public Event nextEvPEC() {
		Event first = E.get(0);
		E.removeFirst();
		return first;
	}

}

