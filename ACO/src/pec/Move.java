package pec;

import java.util.*;

import aco_sim.*;
import graph.Node;

public class Move extends Event{
	
	/*Fields*/
	final double alpha;
	final double beta;
	final double delta;
	final double plevel;
	
	/*Constructor*/
	
	//constructor
	public Move(Object obj, double timestamp, double alpha, double beta, double delta, double plevel) {
		super(obj, timestamp);
		this.alpha = alpha;
		this.beta= beta;
		this.delta = delta;
		this.plevel = plevel;
	}
		
	/*Methods*/
	
	@Override
	public void simulate(PEC pec) {
		// create next event
		pec.addEvPEC(newMove());
	}
	
	//Calculates the following node
	public int nextNode() {		
		Node Current = ((Ant)obj).getPath().get(((Ant)obj).getPath().size()-1);
		LinkedList<Node> Adj = new LinkedList<Node>(); //List of not-visited Adjacent Nodes J to current Node i
		LinkedList<Node> J1 = new LinkedList<Node>();
		Collection<Node> J = new HashSet<Node>();
		
		double ci = 0;
		double w[] = new double[Current.getEdgesSize()];
		double cij[] = new double[w.length];
		Double probability[] = new Double[w.length];
					
		Integer index[] = new Integer[w.length];
		for (int i = 0; i < index.length; i++) {
		    index[i] = i;
		}
		
		//Array with each edge with weight and Adjacent list of Nodes 
		for(int i = 0; i < Current.getEdgesSize(); ++i) {
			w[i] = Current.getEdge(i).getWeight();
			Adj.add(Current.getEdge(i).getNode2());
		}
		
		//Probability Calculation
		for(int k=0; k < w.length ; ++k) {
			cij[k] = (alpha + plevel)/(beta + w[k]); 	 
			ci += (alpha + plevel)/(beta + w[k]); 
		}	
		
		//Probabilities are stored in array.
		Prob uniform = new Prob();
		for(int k=0; k < w.length ; ++k) {
			probability[k] = (uniform.uniformDist(100))*cij[k]/ci; 
		}
		
		//Sorting index , by comparing the probability
		Arrays.sort(index, Collections.reverseOrder(new Comparator<Integer>() {

		    public int compare(Integer i1, Integer i2) {
		        return probability[i1].compareTo(probability[i2]);
		    }
		}));
		
		//Sorting of Nodes, by probability
		for(int i = 0; i < index.length; ++i) {
			J.add(Adj.get(index[i]));
		}
		
		//Remove already visited nodes from array of possibilities
		J.removeAll(((Ant)obj).getPath());
		J1.addAll(J);
		
		if(J.isEmpty()) {
			//if J is empty , then the ant choose a random already visited adjacent node
			return Adj.get(uniform.uniformDist(Adj.size())).getIdx();
		} else {
			//ant chooses non visited node with the highest probability
			return J1.get(0).getIdx();
		}		
	}
		
	//creates next event
	Move newMove() {
		//computes next node
		/* from node i -> last node of path
		 * to node j -> new node calculated
		 * get weight w for edge from i to j
		 * */
		//returns new event
		return new Move(obj, Prob.expRand(delta/**w*/), alpha, beta, delta, plevel);
	}
}