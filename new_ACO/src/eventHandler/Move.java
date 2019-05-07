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
	public void simulate(PEC pec, Graph G,Var v) {
		// create next event
		int current_node = ((Ant)obj).getLast();
		int next_nodeidx = G.nextNode(v, (Ant)obj);
		((Ant)obj).updatePath(next_nodeidx,G,v);
		int real_next = ((Ant)obj).getLast();
		pec.addEvPEC(newMove(v.getDelta(), G.getEdgeWeigth(current_node,real_next)));	
	}

	//creates next eventT
	Move newMove(double delta,double a_ij) {
		return new Move((Ant)obj , Prob.expRand(delta*a_ij));
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
