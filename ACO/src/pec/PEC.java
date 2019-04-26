package pec;

import java.util.LinkedList;

public class PEC {
	//attributes
	LinkedList<Event> E;
	
	//constructor
	public PEC() {
		E =  new LinkedList<Event>();
	}
	
	//adds the received event to the PEC
	public void addEvPEC(Event ev) {
		if(ev != null)
			E.add(ev);
		/*ordenar por timestamp*/
	}
	
	//removes the first event from the PEC and returns it
	public Event nextEvPEC() {
		Event first = E.get(0);
		E.removeFirst();
		return first;
	}
}
