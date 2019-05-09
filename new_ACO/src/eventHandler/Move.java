package eventHandler;

import pec.*;
import xml_utils.Var;
import aco_tools.*;
import graph.IGraph;

public class Move extends Event{
	
	public Move(Object obj, double timestamp) {
		super(obj, timestamp);
	}

	@Override
	public void simulate(PEC pec, IGraph G, Var v) {
		Ant aux = (Ant)obj;
		// create next event
		int current_node = aux.getLast();
		int next_nodeidx = G.nextNode(aux);
		aux.updatePath(next_nodeidx,G,v,pec,getTimestamp());
		int real_next = (aux).getLast();
		double time = Prob.expRand(v.getDelta()*G.getEdgeWeigth(current_node,real_next));
		if(timestamp+time <= v.getFinalinst())
			pec.addEvPEC(newMove(time));
	}

	//creates next eventT
	private Move newMove(double time) {
		return new Move((Ant)obj , timestamp+time);
	}
}