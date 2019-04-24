package aco_sim;

import java.util.LinkedList;

public class PEC {
	//attributes
	LinkedList<Event> E;
	
	//constructor
	PEC() {
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