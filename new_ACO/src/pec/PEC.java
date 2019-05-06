package pec;

import java.util.TreeMap;

public class PEC {
	//attributes
	TreeMap<Double,Event> treeMap;

	//constructor
	public PEC() {
		treeMap = new TreeMap<Double,Event>();
	}

	//adds the received event to the PEC
	public void addEvPEC(Event ev) {
		if(ev != null)
			treeMap.put(ev.timestamp,ev);
		/*ordenar por timestamp*/
	}

	//removes the first event from the PEC and returns it
	public Event nextEvPEC() {
		double key = (treeMap).firstKey();
		Event f = treeMap.get(key);
		treeMap.remove(key);
		return f;
	}
}
