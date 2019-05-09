package eventHandler;

import pec.*;
import xml_utils.Var;
import aco_tools.*;
import graph.Graph;

/**
 * Used to create and manage a move even in the PEC.
 *
 */
public class Move extends Event{
	
	/**
	 * Constructor of Move class.
	 * @param obj Ant.
	 * @param timestamp Current time.
	 */

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

	/**
	 * creates next eventT
	 * 
	 * @param time Time until next move event for current ant.
	 * @return Move.
	 */
	Move newMove(double time) {
		return new Move((Ant)obj , getTimestamp()+time);
	}

}