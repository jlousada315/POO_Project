package aco_sim;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Move extends Event{
	
	/*Fields*/
	final double alpha;
	final double beta;
	final double delta;
	final double plevel;
	
	/*Constructor*/
	
	public Move(double Alpha, double Beta, double Delta,double Plevel) {
		// TODO Auto-generated constructor stub
		alpha = Alpha;
		beta= Beta;
		delta = Delta;
		plevel = Plevel;
	}
	
	/*Methods*/
	
	//add event to PEC with new timestamp
	public void simulate(PEC p,Event ev) {
		p.addEvPEC(ev);
	}
	
	//Calculates the following node
public LinkedList<Node> nextNode(Ant A) {
		
		LinkedList<Node> path = A.getPath();
		Node Current = path.get(path.size()-1);
		LinkedList<Node> Adj = new LinkedList<Node>();
		LinkedList<Node> NodeOrdered = new LinkedList<Node>();
		
		double ci = 1;
		double w[] = new double[Current.edges.size()];
		double cij[] = new double[w.length];
		Double probability[] = new Double[w.length];
		
		Integer index[] = new Integer[w.length];
		for (int i = 0; i < index.length; i++) {
		    index[i] = i;
		}
		
		//Array with each edge with weight and Adjacent list of Nodes 
		for(int i = 0; i < Current.edges.size();++i) {
			w[i] = Current.edges.get(i).weight;
			Adj.add(Current.edges.get(i).node2);
		}
		
		//Probability Calculation
		for(int k=0; k < w.length ; ++k) {
			ci += (alpha + plevel)/(beta + w[k]); 
		}
		
		for(int k=0; k < w.length ; ++k) {
			cij[k] = (alpha + plevel)/(beta + w[k]); 	 
		}
		
		//Probabilities are stored in array.
		for(int k=0; k < w.length ; ++k) {
			probability[k] = cij[k]/ci; 
		}
		
		Arrays.sort(index, Collections.reverseOrder(new Comparator<Integer>() {

		    public int compare(Integer i1, Integer i2) {
		        return probability[i1].compareTo(probability[i2]);
		    }
		}));
		
		for(int i = 0; i < index.length; ++i) {
			NodeOrdered.add(Adj.get(index[i]));
		}
		
		//returns List of Nodes ordered from highest probability of tranversing to lowest
		return NodeOrdered;
	}
	
}
