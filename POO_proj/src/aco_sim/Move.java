package aco_sim;

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
	
	public LinkedList<Node> nextNode(Ant A) {
		
		LinkedList<Node> path = A.getPath();
		Node Current = path.get(path.size()-1);
		LinkedList<Node> Adj = new LinkedList<Node>();
		LinkedList<Node> HighProb = new LinkedList<Node>();
		
		int index = 0;
		double ci = 1;
		double w[] = new double[Current.edges.size()];
		double cij[] = new double[w.length];
		
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
		
		//The event(s) with the highest probability is selected
		for(int k=0; k < w.length-1 ; ++k) {
			if(cij[k+1]/ci >= cij[k]/ci) {
				index = k+1;
			}
		}

		HighProb.add(Adj.get(index));
		
		//returns Node with highest probability of tranversing
		return HighProb;
	}
	
}
