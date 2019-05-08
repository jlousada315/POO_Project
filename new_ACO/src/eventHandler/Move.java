package eventHandler;

import pec.*;
import xml_utils.Var;
import aco_tools.*;
import graph.Graph;

public class Move extends Event{
	
	/*Constructor*/

	public Move(Object obj, double timestamp) {
		super(obj, timestamp);
	}

	/*Methods*/

	@Override
	public void simulate(PEC pec, Graph G, Var v) {
		Ant aux = (Ant)obj;
		// create next event
		int current_node = aux.getLast();
		int next_nodeidx = G.nextNode(aux);
		aux.updatePath(next_nodeidx,G,v,pec,getTimestamp());
		int real_next = (aux).getLast();
		double time = Prob.expRand(v.getDelta()*G.getEdgeWeigth(current_node,real_next));
		if(getTimestamp()+time <= v.getFinalinst())
			pec.addEvPEC(newMove(time));
	}

	//creates next eventT
	Move newMove(double time) {
		return new Move((Ant)obj , getTimestamp()+time);
	}

	/*
	//pretty print a map
	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
			+ " Value : " + entry.getValue());
		}
	}
	void printCollection(Collection<Node> X) {
		for (Iterator<Node> i = X.iterator(); i.hasNext();) {
			Node item = i.next();
			System.out.println(item.toString());		
			}
	}*/
}